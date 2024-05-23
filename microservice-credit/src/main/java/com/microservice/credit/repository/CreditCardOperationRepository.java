package com.microservice.credit.repository;

import com.microservice.credit.entity.CreditCard;
import com.microservice.credit.entity.CreditCardOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CreditCardOperationRepository extends JpaRepository<CreditCardOperation, Long> {
    public Optional<CreditCardOperation> findOneById(Long id);
}
