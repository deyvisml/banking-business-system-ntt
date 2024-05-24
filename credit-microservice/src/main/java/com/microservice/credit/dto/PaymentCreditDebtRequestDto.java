package com.microservice.credit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for Payment Credit Debt Request.
 * This class represents a request for credit debt payment with the specified ID
 * and amount.
 *
 * @param id     The unique identifier of the payment credit debt request
 * @param amount The amount to be paid for the credit debt
 * 
 * @author Deyvis Mamani Lacuta
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCreditDebtRequestDto {
    private Long id;
    private Float amount;
}
