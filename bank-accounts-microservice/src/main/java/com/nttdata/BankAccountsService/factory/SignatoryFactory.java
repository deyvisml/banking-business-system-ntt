package com.nttdata.BankAccountsService.factory;

import com.nttdata.BankAccountsService.model.BankAccount;
import com.nttdata.BankAccountsService.model.Signatory;

import java.sql.Timestamp;
import java.time.Instant;

public class SignatoryFactory {
    /**
     * Creates an instance of a Signatory corresponding to a client.
     * @param clientId Client ID
     * @param bankAccount BankAccount to be assigned.
     * @return A Signatory instance.
     */
    public Signatory createSignatory(Integer clientId , BankAccount bankAccount){
        Signatory signatory = new Signatory();
        signatory.setCreatedAt(Timestamp.from(Instant.now()));
        signatory.setUpdatedAt(Timestamp.from(Instant.now()));
        signatory.setClientId(clientId);
        signatory.setBankAccount(bankAccount);
        return signatory;
    }
}
