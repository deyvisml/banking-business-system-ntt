package com.example.BankAccountsService.factory;

import com.example.BankAccountsService.model.BankAccount;
import com.example.BankAccountsService.model.Holder;

import java.sql.Timestamp;
import java.time.Instant;

public class HolderFactory {
    public Holder createHolder(Integer clientId, BankAccount bankAccount){
        Holder holder = new Holder();
        holder.setCreatedAt(Timestamp.from(Instant.now()));
        holder.setUpdatedAt(Timestamp.from(Instant.now()));
        holder.setClientId(clientId);
        holder.setBankAccount(bankAccount);
        return holder;
    }
}
