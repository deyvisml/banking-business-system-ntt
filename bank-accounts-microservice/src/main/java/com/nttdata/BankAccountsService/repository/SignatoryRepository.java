package com.nttdata.BankAccountsService.repository;

import com.nttdata.BankAccountsService.model.Signatory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignatoryRepository extends JpaRepository<Signatory, Long> {
}