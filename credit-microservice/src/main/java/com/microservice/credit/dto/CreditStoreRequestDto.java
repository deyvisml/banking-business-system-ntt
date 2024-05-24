package com.microservice.credit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing a credit store request.
 *
 * This class encapsulates the details of a credit store request, including loan
 * amount,
 * start date, end date, interest rate, and client ID.
 *
 * Getters and setters are provided for all fields for accessing and modifying
 * the data.
 *
 * An all-args constructor and a no-args constructor are provided for
 * convenience.
 *
 * @param loanAmount   The amount of the loan requested
 * @param startDate    The start date of the loan
 * @param endDate      The end date of the loan
 * @param interestRate The interest rate for the loan
 * @param clientId     The ID of the client requesting the loan
 * 
 * @author Deyvis Mamani Lacuta
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditStoreRequestDto {
    private Float loanAmount;
    private String startDate;
    private String endDate;
    private Float interestRate;
    private Long clientId;
}
