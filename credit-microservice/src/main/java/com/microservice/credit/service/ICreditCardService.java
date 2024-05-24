package com.microservice.credit.service;

import com.microservice.credit.dto.*;
import com.microservice.credit.entity.CreditCard;
import com.microservice.credit.entity.CreditCardOperation;

import java.util.List;

/**
 * This interface defines the operations that can be performed on credit cards.
 * 
 * @author Deyvis Mamani Lacuta
 */
public interface ICreditCardService {
    /**
     * Retrieves all credit cards from the database.
     *
     * @return A list of all credit cards stored in the database
     */
    public List<CreditCard> findAll();

    /**
     * Finds and returns a credit card by its ID.
     *
     * @param id The ID of the credit card to find
     * @return The credit card object if found, or null if not found
     */
    public CreditCard findCreditCardById(Long id);

    /**
     * Stores credit card information based on the provided
     * CreditCardStoreRequestDto.
     *
     * @param creditCardStoreRequestDto The data transfer object containing credit
     *                                  card information
     * @return An object representing the result of the credit card storage
     *         operation
     */
    public Object storeCreditCard(CreditCardStoreRequestDto creditCardStoreRequestDto);

    /**
     * Makes a debt payment using the provided PaymentDebtRequestDto.
     *
     * @param paymentDebtRequestDto The DTO containing payment information
     * @return The CreditCardOperation representing the debt payment
     */
    public CreditCardOperation makeDebtPayment(PaymentDebtRequestDto paymentDebtRequestDto);

    /**
     * Makes a charge using the provided credit card charge request data.
     *
     * @param creditCardChargeRequestDto The data for the credit card charge request
     * @return The credit card operation resulting from the charge
     */
    public CreditCardOperation makeCharge(CreditCardChargeRequestDto creditCardChargeRequestDto);

    /**
     * Retrieves the available amount based on the provided request data.
     *
     * @param availableAmountRequestDto The data object containing the request
     *                                  details
     * @return The available amount as a Float value
     */
    public Float getAvailableAmount(AvailableAmountRequestDto availableAmountRequestDto);

    /**
     * Retrieves a list of credit cards associated with a specific client ID.
     *
     * @param clientId The unique identifier of the client
     * @return A list of CreditCard objects belonging to the client
     */
    public List<CreditCard> findCreditCardsByClientId(Long clientId);
}
