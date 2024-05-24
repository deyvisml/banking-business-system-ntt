package com.microservice.credit.repository;

import com.microservice.credit.entity.CreditCardOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for CreditCardOperation entities.
 * Extends JpaRepository to provide basic CRUD operations.
 * 
 * @author Deyvis Mamani Lacuta
 */
@Repository
public interface CreditCardOperationRepository extends JpaRepository<CreditCardOperation, Long> {
    /**
     * Retrieves a CreditCardOperation entity by its ID.
     *
     * @param id The ID of the CreditCardOperation entity to find
     * @return An Optional containing the CreditCardOperation entity if found,
     *         otherwise empty
     */
    public Optional<CreditCardOperation> findOneById(Long id);
}
