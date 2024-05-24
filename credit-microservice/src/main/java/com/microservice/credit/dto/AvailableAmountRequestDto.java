package com.microservice.credit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing a request for available amount.
 *
 * This class encapsulates the details required for checking the available
 * amount.
 *
 * @param cardNumber   The card number for which the available amount is
 *                     requested.
 * @param expiryMonth  The expiry month of the card.
 * @param expiryYear   The expiry year of the card.
 * @param securityCode The security code of the card.
 * 
 * @author Deyvis Mamani Lacuta
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableAmountRequestDto {
    String cardNumber;
    Integer expiryMonth;
    Integer expiryYear;
    String securityCode;
}
