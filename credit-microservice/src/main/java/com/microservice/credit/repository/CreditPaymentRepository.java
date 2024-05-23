package com.microservice.credit.repository;

import com.microservice.credit.entity.CreditPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditPaymentRepository extends JpaRepository<CreditPayment, Long> {
    public Optional<CreditPayment> findOneById(Long id);
}
