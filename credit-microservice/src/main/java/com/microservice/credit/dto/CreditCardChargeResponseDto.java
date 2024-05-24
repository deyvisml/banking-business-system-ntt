package com.microservice.credit.dto;

import com.microservice.credit.entity.CreditCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing the response of a credit card charge
 * operation.
 *
 * This class encapsulates the status of the charge, a message related to the
 * charge,
 * and the credit card information used for the charge.
 *
 * Getters and setters are provided for all fields, along with constructors for
 * creating instances with and without initial values.
 * 
 * @author Deyvis Mamani Lacuta
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardChargeResponseDto {
    private Boolean status;
    private String message;
    private CreditCard creditCard;
}
