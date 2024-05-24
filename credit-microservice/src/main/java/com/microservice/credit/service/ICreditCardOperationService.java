package com.microservice.credit.service;

import com.microservice.credit.dto.*;
import com.microservice.credit.entity.CreditCard;
import com.microservice.credit.entity.CreditCardOperation;

import java.util.List;

/**
 * This interface defines operations related to credit card operations.
 * 
 * @author Deyvis Mamani Lacuta
 */
public interface ICreditCardOperationService {
    /**
     * Retrieves all credit card operations from the database.
     *
     * @return A list of all credit card operations
     */
    public List<CreditCardOperation> findAll();

    /**
     * Finds a credit card operation by its ID.
     *
     * @param id The ID of the credit card operation to find
     * @return The credit card operation with the specified ID, or null if not found
     */
    public CreditCardOperation findCreditCardOperationById(Long id);
}
