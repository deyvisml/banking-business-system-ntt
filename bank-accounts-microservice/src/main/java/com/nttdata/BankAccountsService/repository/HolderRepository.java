package com.nttdata.BankAccountsService.repository;

import com.nttdata.BankAccountsService.model.Holder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolderRepository extends JpaRepository<Holder, Long> {
}