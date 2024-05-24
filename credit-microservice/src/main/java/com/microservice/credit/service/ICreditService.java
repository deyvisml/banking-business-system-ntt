package com.microservice.credit.service;

import com.microservice.credit.dto.*;
import com.microservice.credit.entity.Credit;
import com.microservice.credit.entity.CreditPayment;

import java.util.List;

/**
 * This interface defines the operations that can be performed on credits.
 */
public interface ICreditService {
    /**
     * Retrieves all credits from the database.
     *
     * @return A list of all credits stored in the database
     */
    public List<Credit> findAll();

    /**
     * Finds and returns a Credit object by its ID.
     *
     * @param id The ID of the Credit object to find
     * @return The Credit object with the specified ID, or null if not found
     */
    public Credit findCreditById(Long id);

    /**
     * Stores credit based on the provided CreditStoreRequestDto.
     *
     * @param creditStoreRequestDto The data transfer object containing credit store
     *                              information
     * @return An object representing the result of the credit storage operation
     */
    public Object storeCredit(CreditStoreRequestDto creditStoreRequestDto);

    /**
     * Makes a payment towards a credit debt using the provided payment information.
     *
     * @param paymentCreditDebtRequestDto The DTO containing payment information for
     *                                    the credit debt
     * @return The CreditPayment object representing the payment made
     */
    public CreditPayment makeDebtPayment(PaymentCreditDebtRequestDto paymentCreditDebtRequestDto);

    /**
     * Finds and returns a list of credits associated with a specific client ID.
     *
     * @param clientId The unique identifier of the client
     * @return A list of Credit objects related to the client ID
     */
    public List<Credit> findCreditsByClientId(Long clientId);
}
