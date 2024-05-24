package com.nttdata.BankAccountsService.controller;

import com.nttdata.BankAccountsService.DTO.BankAccountDTO;
import com.nttdata.BankAccountsService.DTO.OperationDTO;
import com.nttdata.BankAccountsService.exception.RequestException;
import com.nttdata.BankAccountsService.model.BankAccount;
import com.nttdata.BankAccountsService.model.BankAccountLog;
import com.nttdata.BankAccountsService.response.BaseResponse;
import com.nttdata.BankAccountsService.service.BankAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BankAccountControllerTest {
    @InjectMocks
    BankAccountController bankAccountController;
    @Mock
    BankAccountService bankAccountService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate_Sucess(){
        Integer clientId = 1;
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        BankAccount expectedBankAccount = new BankAccount();

        when(bankAccountService.createAccount(clientId, bankAccountDTO)).thenReturn(expectedBankAccount);

        ResponseEntity<?> responseEntity = bankAccountController.create(bankAccountDTO, clientId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        BaseResponse<?> responseBody = (BaseResponse<?>) responseEntity.getBody();
        assertEquals("Bank account created successfully.", responseBody.getMessage());
        assertEquals(true, responseBody.getSuccess());
        assertEquals(expectedBankAccount, responseBody.getData());
    }

    @Test
    void testCreate_Exception(){
        Integer clientId = 1;
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        String errorMessage = "An error occurred";

        when(bankAccountService.createAccount(clientId, bankAccountDTO)).thenThrow(new RequestException(errorMessage));

        ResponseEntity<?> responseEntity = bankAccountController.create(bankAccountDTO, clientId);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        BaseResponse<?> responseBody = (BaseResponse<?>) responseEntity.getBody();
        assertEquals(errorMessage, responseBody.getMessage());
        assertEquals(false, responseBody.getSuccess());
        assertNull(responseBody.getData());
    }

    @Test
    void testUpdateBalance_Success() {
        Integer accountId = 1;
        OperationDTO operationDTO = new OperationDTO();
        BankAccountLog expectedLog = new BankAccountLog();

        when(bankAccountService.updateBalance(accountId, operationDTO)).thenReturn(expectedLog);

        ResponseEntity<?> responseEntity = bankAccountController.updateBalance(accountId, operationDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        BaseResponse<?> responseBody = (BaseResponse<?>) responseEntity.getBody();
        assertEquals("Operation carried out successfully.", responseBody.getMessage());
        assertEquals(true, responseBody.getSuccess());
        assertEquals(expectedLog, responseBody.getData());
    }

    @Test
    void testUpdateBalance_Exception() {
        Integer accountId = 1;
        OperationDTO operationDTO = new OperationDTO();
        String errorMessage = "An error occurred";

        when(bankAccountService.updateBalance(accountId, operationDTO)).thenThrow(new RequestException(errorMessage));

        ResponseEntity<?> responseEntity = bankAccountController.updateBalance(accountId, operationDTO);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        BaseResponse<?> responseBody = (BaseResponse<?>) responseEntity.getBody();
        assertEquals(errorMessage, responseBody.getMessage());
        assertEquals(false, responseBody.getSuccess());
        assertNull(responseBody.getData());
    }

    @Test
    void testClientAccounts_Success() {
        Integer clientId = 1;
        Set<BankAccount> expectedAccounts = new HashSet<>();
        expectedAccounts.add(new BankAccount());

        when(bankAccountService.findAccountsByClientId(clientId)).thenReturn(expectedAccounts);

        ResponseEntity<?> responseEntity = bankAccountController.clientAccounts(clientId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        BaseResponse<?> responseBody = (BaseResponse<?>) responseEntity.getBody();
        assertEquals("Client accounts successfully obtained.", responseBody.getMessage());
        assertEquals(true, responseBody.getSuccess());
        assertEquals(expectedAccounts, responseBody.getData());
    }

    @Test
    void testClientAccounts_Exception() {
        Integer clientId = 1;
        String errorMessage = "An error occurred";

        when(bankAccountService.findAccountsByClientId(clientId)).thenThrow(new RequestException(errorMessage));

        ResponseEntity<?> responseEntity = bankAccountController.clientAccounts(clientId);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        BaseResponse<?> responseBody = (BaseResponse<?>) responseEntity.getBody();
        assertEquals(errorMessage, responseBody.getMessage());
        assertEquals(false, responseBody.getSuccess());
        assertNull(responseBody.getData());
    }

}