package com.nttdata.BankAccountsService.service;

import com.nttdata.BankAccountsService.DTO.BankAccountDTO;
import com.nttdata.BankAccountsService.DTO.OperationDTO;
import com.nttdata.BankAccountsService.exception.RequestException;
import com.nttdata.BankAccountsService.model.BankAccount;
import com.nttdata.BankAccountsService.repository.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BankAccountServiceTest {
    @InjectMocks
    private BankAccountService bankAccountService;
    @Mock
    private BankAccountRepository bankAccountRepository;
    @Mock
    private ClientServiceClient clientServiceClient;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAccount_BusinessClient_Success() {
        Integer clientId = 1;
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setType("Corriente");

        when(clientServiceClient.getClientTypeById(1)).thenReturn("personal");
        when(bankAccountRepository.save(any(BankAccount.class))).thenAnswer(invocation -> invocation.getArgument(0));

        BankAccount result = bankAccountService.createAccount(clientId, bankAccountDTO);

        assertNotNull(result);
        verify(bankAccountRepository).save(any(BankAccount.class));
    }

    @Test
    void testCreateAccount_BusinessClient_InvalidType() {
        Integer clientId = 1;
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setType("Ahorros");
        when(clientServiceClient.getClientTypeById(1)).thenReturn("empresarial");
        RequestException exception = assertThrows(RequestException.class, () -> {
            bankAccountService.createAccount(clientId, bankAccountDTO);
        });
        assertEquals("Business clients can only have checking accounts.", exception.getMessage());
    }

    @Test
    void findAccountsByClientId_Found() {
        Integer bankAccountId = 1;
        BankAccount expectedBankAccount = new BankAccount();
        expectedBankAccount.setId(bankAccountId);
        when(bankAccountRepository.findById(bankAccountId)).thenReturn(Optional.of(expectedBankAccount));
        BankAccount actualBankAccount = bankAccountService.findBankAccountById(bankAccountId);

        assertNotNull(actualBankAccount);
        assertEquals(expectedBankAccount , actualBankAccount);
    }

    @Test
    void findAccountsById_NotFound(){
        Integer bankAccountId = 1;
        when(bankAccountRepository.findById(bankAccountId)).thenReturn(Optional.empty());
        BankAccount actualBankAccount = bankAccountService.findBankAccountById(bankAccountId);
        assertNull(actualBankAccount);
    }

    @Test
    void testUpdateBalance_AccountNotFound() {
        Integer accountId = 1;
        OperationDTO operationDTO = new OperationDTO();
        operationDTO.setType("Deposito");
        operationDTO.setAmount(100.0f);
        operationDTO.setClient_id(1);

        when(bankAccountRepository.findById(accountId)).thenReturn(Optional.empty());

        RequestException exception = assertThrows(RequestException.class, () -> {
            bankAccountService.updateBalance(accountId, operationDTO);
        });
        assertEquals("Account not found.", exception.getMessage());
    }

    @Test
    void testAddHolder_Success() {
        Integer clientId = 1;
        Integer bankAccountId = 1;
        BankAccount bankAccount = new BankAccount();
        when(bankAccountRepository.findById(bankAccountId)).thenReturn(Optional.of(bankAccount));
        when(bankAccountRepository.save(bankAccount)).thenReturn(bankAccount);

        BankAccount result = bankAccountService.addHolder(clientId, bankAccountId);

        assertNotNull(result);
        assertEquals(clientId, result.getHolders().iterator().next().getClientId());
        verify(bankAccountRepository).findById(bankAccountId);
        verify(bankAccountRepository).save(bankAccount);
    }

    @Test
    void testAddHolder_BankAccountNotFound() {
        Integer clientId = 1;
        Integer bankAccountId = 1;
        when(bankAccountRepository.findById(bankAccountId)).thenReturn(Optional.empty());

        RequestException exception = assertThrows(RequestException.class, () -> {
            bankAccountService.addHolder(clientId, bankAccountId);
        });
        assertEquals("Bank account not found.", exception.getMessage());
    }

    @Test
    void testAddSignatory_Success() {
        Integer clientId = 1;
        Integer bankAccountId = 1;
        BankAccount bankAccount = new BankAccount();

        when(bankAccountRepository.findById(bankAccountId)).thenReturn(Optional.of(bankAccount));
        when(bankAccountRepository.save(bankAccount)).thenReturn(bankAccount);

        BankAccount result = bankAccountService.addSignatory(clientId, bankAccountId);

        assertNotNull(result);
        assertEquals(clientId, result.getSignatories().iterator().next().getClientId());
        verify(bankAccountRepository).findById(bankAccountId);
        verify(bankAccountRepository).save(bankAccount);
    }

    @Test
    void testAddSignatory_BankAccountNotFound() {
        Integer clientId = 1;
        Integer bankAccountId = 1;
        when(bankAccountRepository.findById(bankAccountId)).thenReturn(Optional.empty());

        RequestException exception = assertThrows(RequestException.class, () -> {
            bankAccountService.addSignatory(clientId, bankAccountId);
        });
        assertEquals("Bank account not found.", exception.getMessage());
    }

    @Test
    void testDeleteAccount_Success() {
        Integer accountId = 1;
        when(bankAccountRepository.existsById(accountId)).thenReturn(true);

        boolean result = bankAccountService.deleteAccount(accountId);

        assertTrue(result);
        verify(bankAccountRepository).deleteById(accountId);
    }

    @Test
    void testDeleteAccount_AccountNotFound() {
        Integer accountId = 1;
        when(bankAccountRepository.existsById(accountId)).thenReturn(false);

        RequestException exception = assertThrows(RequestException.class, () -> {
            bankAccountService.deleteAccount(accountId);
        });
        assertEquals("Account not found.", exception.getMessage());
        verify(bankAccountRepository, never()).deleteById(accountId);
    }
}