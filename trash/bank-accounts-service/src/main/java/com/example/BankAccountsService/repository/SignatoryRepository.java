package com.example.BankAccountsService.repository;

import com.example.BankAccountsService.model.Signatory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignatoryRepository extends JpaRepository<Signatory, Long> {
}