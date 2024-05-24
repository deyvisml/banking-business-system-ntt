package com.example.BankAccountsService.service;

import com.example.BankAccountsService.DTO.BankAccountDTO;
import com.example.BankAccountsService.DTO.OperationDTO;
import com.example.BankAccountsService.exception.RequestException;
import com.example.BankAccountsService.factory.BankAccountFactory;
import com.example.BankAccountsService.factory.BankAccountLogFactory;
import com.example.BankAccountsService.factory.HolderFactory;
import com.example.BankAccountsService.factory.SignatoryFactory;
import com.example.BankAccountsService.model.BankAccount;
import com.example.BankAccountsService.model.BankAccountLog;
import com.example.BankAccountsService.model.Holder;
import com.example.BankAccountsService.model.Signatory;
import com.example.BankAccountsService.repository.BankAccountLogRepository;
import com.example.BankAccountsService.repository.BankAccountRepository;
import com.example.BankAccountsService.repository.HolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankAccountLogRepository bankAccountLogRepository;

    @Autowired
    private HolderRepository holderRepository;
    @Autowired
    private ClientServiceClient clientServiceClient;
    private BankAccount findBankAccountById(Integer bankAccountId) {
        return bankAccountRepository.findById(bankAccountId)
                .orElse(null);
    }

    private boolean validateClient(BankAccount bankAccount, Integer clientId) {
        return bankAccount.getHolders().stream()
                .anyMatch(holder -> Objects.equals(holder.getClientId(), clientId)) ||
                bankAccount.getSignatories().stream()
                        .anyMatch(signatory -> Objects.equals(signatory.getClientId(), clientId));
    }

    /**
     * Validates the limit of monthly operations that a specific account has.
     * @param bankAccount Bank account object.
     * @return A boolean value that determines whether further operations can be performed or not
     */
    private boolean validateMonthlyLimit(BankAccount bankAccount) {
        return bankAccount.getMonthlyOperationsExecuted() < bankAccount.getMonthlyOperationsLimit();
    }
    /**
     * Creates a new Bank Account from a client ID.
     * @param clientId Client ID
     * @return The new Bank Account created.
     */
    @Transactional
    public BankAccount createAccount(Integer clientId, BankAccountDTO bankAccountDTO){
        String clientType = clientServiceClient.getClientTypeById(clientId);
        String type = bankAccountDTO.getType();
        if(clientType.equals("personal")){
            Set<?> client_accounts = findAccountsByClientId(clientId);
            long existingAccounts = 0;
            if(client_accounts != null)
                existingAccounts += client_accounts.size();
            if(existingAccounts >= 1) {
                throw new RequestException("Personal clients can only have one account.");
            }
        }
        else if(clientType.equals("empresarial")){
            if(!type.equals("Corriente")){
                throw new RequestException("Business clients can only have checking accounts.");
            }
        }

        BankAccount bankAccount = new BankAccountFactory().createBankAccount(bankAccountDTO);
        Holder holder = new HolderFactory().createHolder(clientId , bankAccount);

        Set<Holder> holders = new HashSet<>();
        holders.add(holder);
        bankAccount.setHolders(holders);

        return bankAccountRepository.save(bankAccount);
    }
    /**
     * Get all accounts of database
     * @return List of all Accounts.
     */
    public List<BankAccount> getAllAccounts(){
        return bankAccountRepository.findAll();
    }

    /**
     * Find all accounts of a Client
     * @param client_Id Client ID
     * @return A list of all accounts of a specific client.
     */
    public Set<BankAccount> findAccountsByClientId(Integer client_Id) {
        List<BankAccount> signatoriesAccounts = bankAccountRepository.findBySignatoriesClientId(client_Id);
        List<BankAccount> holdersAccounts = bankAccountRepository.findByHoldersClientId(client_Id);

        return Stream.concat(signatoriesAccounts.stream(), holdersAccounts.stream())
                .filter(account -> account.getHolders().stream().anyMatch(holder -> holder.getClientId().equals(client_Id)) ||
                        account.getSignatories().stream().anyMatch(signatory -> signatory.getClientId().equals(client_Id)))
                .collect(Collectors.toSet());
    }

    /**
     * Updates the balance from an account by a client query.
     * @param accountId    Client  ID
     * @param operationDTO   Operation body
     * @return A string that specifies the result of the operation.
     */
    @Transactional
    public BankAccountLog updateBalance(Integer accountId , OperationDTO operationDTO) {
        String operationType = operationDTO.getType();
        Float amount = operationDTO.getAmount();
        Integer clientId = operationDTO.getClient_id();
        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(accountId);
        if (optionalBankAccount.isPresent()) {
            BankAccount bankAccount = optionalBankAccount.get();
            if (!validateMonthlyLimit(bankAccount)){
                throw new RequestException("Move Denied - Monthly Trading Limit Reached");
            }
            if (validateClient(bankAccount , clientId)) {
                Float newBalance = bankAccount.getBalance();
                if (operationType.equals("Deposito")) {
                    newBalance += amount;
                }
                else if(operationType.equals("Retiro")){
                    if (newBalance >= amount) {
                        newBalance -= amount;
                    } else {
                        throw new RequestException("Insufficient balance.");
                    }
                }
                BankAccountLog log = new BankAccountLogFactory().createLog(bankAccount , amount , newBalance , operationType);
                bankAccount = new BankAccountFactory().updateBankAccountBalance(newBalance , bankAccount);
                bankAccount.getLogs().add(log);
                bankAccountRepository.save(bankAccount);
                return log;
            } else {
                throw new RequestException("Movement Denied - You are not the holder or signatory of this account.");
            }
        } else {
            throw new RequestException("Account not found.");
        }
    }

    /**
     * Adds a new holder to the holder list from an existing account.
     * @param clientId Client ID
     * @param bank_account_id Bank account ID
     * @return The updated account.
     */
    @Transactional
    public BankAccount addHolder (Integer clientId , Integer bank_account_id){
        BankAccount bankAccount = findBankAccountById(bank_account_id);
        if(bankAccount == null)
        {
            throw new RequestException("Bank account not found.");
        }
        else{
            Holder holder = new HolderFactory().createHolder(clientId , bankAccount);
            bankAccount.updateHolders(holder);
            return bankAccountRepository.save(bankAccount);
        }
    }

    /**
     * Adds a new signatory to the signatory list from an existing account.
     * @param clientId Client ID
     * @param bank_account_id Bank account ID
     * @return The updated account.
     */
    @Transactional
    public BankAccount addSignatory (Integer clientId , Integer bank_account_id){
        BankAccount bankAccount = findBankAccountById(bank_account_id);
        if(bankAccount == null)
        {
            throw new RequestException("Bank account not found.");
        }
        else{
            Signatory signatory = new SignatoryFactory().createSignatory(clientId , bankAccount);
            bankAccount.updateSignatories(signatory);
            return bankAccountRepository.save(bankAccount);
        }
    }
    /**
     * Deletes an account by its ID
     * @param accountId Account ID
     * @return A boolean that determines the success from the operation.
     */
    @Transactional
    public boolean deleteAccount(Integer accountId) {
        boolean exists = bankAccountRepository.existsById(accountId);
        if(exists){
            bankAccountRepository.deleteById(accountId);
            return true;
        }
        else
            throw new RequestException("Account not found.");
    }
}
