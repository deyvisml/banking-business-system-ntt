package com.microservice.credit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) class for storing credit card information.
 *
 * This class represents a request to store credit card details including expiry
 * month, expiry year,
 * limit amount, interest rate, and client ID.
 *
 * @param expiryMonth  The month of the credit card expiry date
 * @param expiryYear   The year of the credit card expiry date
 * @param limitAmount  The credit card limit amount
 * @param interestRate The interest rate associated with the credit card
 * @param clientId     The ID of the client associated with the credit card
 * 
 * @author Deyvis Mamani Lacuta
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardStoreRequestDto {
    private Integer expiryMonth;
    private Integer expiryYear;
    private Float limitAmount;
    private Float interestRate;
    private Long clientId;
}
