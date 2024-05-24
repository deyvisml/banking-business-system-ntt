package com.example.BankAccountsService.factory;

import com.example.BankAccountsService.DTO.BankAccountDTO;
import com.example.BankAccountsService.model.BankAccount;
import com.example.BankAccountsService.model.BankAccountLog;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;

public class BankAccountFactory {
    public BankAccount createBankAccount (BankAccountDTO bankAccountDTO){
        BankAccount bankAccount = new BankAccount();
        bankAccount.setUpdatedAt(Timestamp.from(Instant.now()));
        bankAccount.setCreatedAt(Timestamp.from(Instant.now()));
        bankAccount.setBalance(bankAccountDTO.getBalance());
        bankAccount.setType(bankAccountDTO.getType());
        bankAccount.setMonthlyOperationsExecuted(0);
        bankAccount.setMonthlyMaintenanceFee(bankAccountDTO.getMonthlyMaintenanceFee());
        bankAccount.setMonthlyOperationsLimit(bankAccountDTO.getMonthlyOperationsLimit());
        return bankAccount;
    }

    public BankAccount updateBankAccountBalance(Float newBalance , BankAccount bankAccount){
        bankAccount.setBalance(newBalance);
        bankAccount.setMonthlyOperationsExecuted(bankAccount.getMonthlyOperationsExecuted() + 1);
        bankAccount.setUpdatedAt(Timestamp.from(Instant.now()));
        return bankAccount;
    }
}
