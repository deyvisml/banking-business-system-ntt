package com.microservice.credit.factory;

import com.microservice.credit.entity.Credit;
import com.microservice.credit.entity.CreditPayment;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * A factory class for creating CreditPayment objects.
 * 
 * @author Deyvis Mamani Lacuta
 */
public class CreditPaymentFactory {
    /**
     * Creates a new CreditPayment object with the specified amount and credit.
     *
     * @param amount The amount of credit to be paid
     * @param credit The credit object associated with the payment
     * @return A new CreditPayment object with the specified amount, credit, and
     *         timestamps
     */
    public CreditPayment createCreditPayment(Float amount, Credit credit) {
        CreditPayment creditPayment = new CreditPayment();
        creditPayment.setAmount(amount);
        creditPayment.setCredit(credit);
        creditPayment.setCreatedAt(Timestamp.from(Instant.now()));
        creditPayment.setUpdatedAt(Timestamp.from(Instant.now()));
        return creditPayment;
    }
}
