package com.nttdata.BankAccountsService.factory;

import com.nttdata.BankAccountsService.model.BankAccount;
import com.nttdata.BankAccountsService.model.Holder;

import java.sql.Timestamp;
import java.time.Instant;

public class HolderFactory {
    /**
     * Creates an instance of a Holder corresponding to a client.
     * @param clientId  Client ID
     * @param bankAccount BankAccount to be assigned.
     * @return A holder instance.
     */
    public Holder createHolder(Integer clientId, BankAccount bankAccount){
        Holder holder = new Holder();
        holder.setCreatedAt(Timestamp.from(Instant.now()));
        holder.setUpdatedAt(Timestamp.from(Instant.now()));
        holder.setClientId(clientId);
        holder.setBankAccount(bankAccount);
        return holder;
    }
}
