package com.microservice.credit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a credit card charge request.
 *
 * This class encapsulates the details required to process a credit card charge.
 *
 * @param cardNumber   The credit card number
 * @param expiryMonth  The expiration month of the credit card
 * @param expiryYear   The expiration year of the credit card
 * @param securityCode The security code of the credit card
 * @param amount       The amount to be charged
 * 
 * @author Deyvis Mamani Lacuta
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardChargeRequestDto {
    String cardNumber;
    Integer expiryMonth;
    Integer expiryYear;
    String securityCode;
    Float amount;
}
