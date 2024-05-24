package com.example.BankAccountsService.repository;

import com.example.BankAccountsService.model.BankAccountLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountLogRepository extends JpaRepository<BankAccountLog, Long> {
    List<BankAccountLog> findByBankAccountId(Integer bankAccountId);
}
