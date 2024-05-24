package com.example.BankAccountsService.repository;

import com.example.BankAccountsService.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    List<BankAccount> findByHoldersClientId(Integer client_id);
    List<BankAccount> findBySignatoriesClientId(Integer client_id);
}
