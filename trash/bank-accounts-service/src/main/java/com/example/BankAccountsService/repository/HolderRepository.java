package com.example.BankAccountsService.repository;

import com.example.BankAccountsService.model.Holder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolderRepository extends JpaRepository<Holder, Long> {
}