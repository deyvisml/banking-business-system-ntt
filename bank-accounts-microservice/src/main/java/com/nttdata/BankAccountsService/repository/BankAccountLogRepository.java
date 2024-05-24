package com.nttdata.BankAccountsService.repository;

import com.nttdata.BankAccountsService.model.BankAccountLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountLogRepository extends JpaRepository<BankAccountLog, Long> {
    List<BankAccountLog> findByBankAccountId(Integer bankAccountId);
}
