package com.microservice.credit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing a payment debt request.
 *
 * This class encapsulates the information required for a payment debt request.
 *
 * @param cardNumber The card number associated with the payment debt request
 * @param amount     The amount of the payment debt request
 * 
 * @author Deyvis Mamani Lacuta
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDebtRequestDto {
    private String cardNumber;
    private Float amount;
}
