package com.example.BankAccountsService.factory;

import com.example.BankAccountsService.model.BankAccount;
import com.example.BankAccountsService.model.BankAccountLog;

import java.sql.Timestamp;
import java.time.Instant;

public class BankAccountLogFactory {
    public BankAccountLog createLog(BankAccount bankAccount, Float amount , Float newBalance, String operationType){
        BankAccountLog bankAccountLog = new BankAccountLog();
        bankAccountLog.setOperationType(operationType);
        bankAccountLog.setOperationAmount(amount);
        bankAccountLog.setBalanceBeforeOperation(bankAccount.getBalance());
        bankAccountLog.setBalanceAfterOperation(newBalance);
        bankAccountLog.setBankAccount(bankAccount);
        bankAccountLog.setCreatedAt(Timestamp.from(Instant.now()));
        bankAccountLog.setUpdatedAt(Timestamp.from(Instant.now()));
        return bankAccountLog;
    }
}
