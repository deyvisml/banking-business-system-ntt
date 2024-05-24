package com.nttdata.BankAccountsService.factory;

import com.nttdata.BankAccountsService.DTO.BankAccountDTO;
import com.nttdata.BankAccountsService.model.BankAccount;

import java.sql.Timestamp;
import java.time.Instant;

public class BankAccountFactory {
    /**
     * Creates an instance of a Bank Account.
     *
     * @param bankAccountDTO - Bank account model and data.
     * @return A BankAccount object.
     */
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

    /**
     * Updates the balance and other related properties of a bank account.
     *
     * @param newBalance The new balance to be set for the bank account.
     * @param bankAccount The BankAccount object to be updated.
     * @return The updated BankAccount object.
     */
    public BankAccount updateBankAccountBalance(Float newBalance , BankAccount bankAccount){
        bankAccount.setBalance(newBalance);
        bankAccount.setMonthlyOperationsExecuted(bankAccount.getMonthlyOperationsExecuted() + 1);
        bankAccount.setUpdatedAt(Timestamp.from(Instant.now()));
        return bankAccount;
    }
}
