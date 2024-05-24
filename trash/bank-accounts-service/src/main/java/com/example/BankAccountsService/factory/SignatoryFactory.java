package com.example.BankAccountsService.factory;

import com.example.BankAccountsService.model.BankAccount;
import com.example.BankAccountsService.model.Holder;
import com.example.BankAccountsService.model.Signatory;

import java.sql.Timestamp;
import java.time.Instant;

public class SignatoryFactory {
    public Signatory createSignatory(Integer clientId , BankAccount bankAccount){
        Signatory signatory = new Signatory();
        signatory.setCreatedAt(Timestamp.from(Instant.now()));
        signatory.setUpdatedAt(Timestamp.from(Instant.now()));
        signatory.setClientId(clientId);
        signatory.setBankAccount(bankAccount);
        return signatory;
    }
}
