package com.microservice.credit.repository;

import com.microservice.credit.entity.CreditPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing CreditPayment entities.
 * Extends JpaRepository to provide basic CRUD operations for CreditPayment
 * entities.
 * 
 * @author Deyvis Mamani Lacuta
 */
@Repository
public interface CreditPaymentRepository extends JpaRepository<CreditPayment, Long> {
    /**
     * Retrieves a CreditPayment entity by its ID.
     *
     * @param id The ID of the CreditPayment entity to find
     * @return An Optional containing the CreditPayment entity if found, otherwise
     *         empty
     */
    public Optional<CreditPayment> findOneById(Long id);
}
