package com.nttdata.BankAccountsService.factory;

import com.nttdata.BankAccountsService.model.BankAccount;
import com.nttdata.BankAccountsService.model.BankAccountLog;

import java.sql.Timestamp;
import java.time.Instant;

public class BankAccountLogFactory {
    /**
     * Creates a new log entry for a bank account operation.
     *
     * @param bankAccount The bank account for which the log is created.
     * @param amount The amount involved in the operation.
     * @param newBalance The balance after the operation is performed.
     * @param operationType The type of operation (e.g., "Deposito" or "Retiro").
     * @return The created BankAccountLog object.
     */
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
