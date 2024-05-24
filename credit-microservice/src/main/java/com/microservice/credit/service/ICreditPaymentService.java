package com.microservice.credit.service;

import com.microservice.credit.entity.CreditPayment;

import java.util.List;

/**
 * This interface represents a credit payment service that provides methods to
 * retrieve credit payment information.
 * 
 * @author Deyvis Mamani Lacuta
 */
public interface ICreditPaymentService {
    /**
     * Retrieves all credit payments from the database.
     *
     * @return A list of CreditPayment objects representing all credit payments
     */
    public List<CreditPayment> findAll();

    /**
     * Finds a credit payment by its unique identifier.
     *
     * @param id The unique identifier of the credit payment to find
     * @return The credit payment object if found, otherwise null
     */
    public CreditPayment findCreditPaymentById(Long id);
}
