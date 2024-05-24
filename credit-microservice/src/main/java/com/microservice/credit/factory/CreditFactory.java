package com.microservice.credit.factory;

import com.microservice.credit.entity.Credit;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * A factory class for creating Credit objects with the provided details.
 * 
 * @author Deyvis Mamani Lacuta
 */
public class CreditFactory {
    /**
     * Creates a new Credit entity with the provided details.
     *
     * @param loanAmount   The amount of the loan
     * @param startDate    The start date of the credit
     * @param endDate      The end date of the credit
     * @param interestRate The interest rate for the credit
     * @param clientId     The ID of the client associated with the credit
     * @return The newly created Credit entity
     */
    public Credit createCredit(Float loanAmount, String startDate, String endDate, Float interestRate, Long clientId) {
        Credit credit = new Credit();
        credit.setLoanAmount(loanAmount);
        credit.setAmount(loanAmount + loanAmount * interestRate / 100);
        credit.setAmountPaid(0f);
        credit.setStartDate(Date.valueOf(startDate));
        credit.setEndDate(Date.valueOf(endDate));
        credit.setInterestRate(interestRate);
        credit.setClientId(clientId);
        credit.setStatus("activo");
        credit.setCreatedAt(Timestamp.from(Instant.now()));
        credit.setUpdatedAt(Timestamp.from(Instant.now()));
        return credit;
    }
}
