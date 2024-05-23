package com.microservice.credit.factory;

import com.microservice.credit.entity.Credit;
import com.microservice.credit.entity.CreditPayment;

import java.sql.Timestamp;
import java.time.Instant;

public class CreditPaymentFactory {
    public CreditPayment createCreditPayment(Float amount, Credit credit) {
        CreditPayment creditPayment = new CreditPayment();
        creditPayment.setAmount(amount);
        creditPayment.setCredit(credit);
        creditPayment.setCreatedAt(Timestamp.from(Instant.now()));
        creditPayment.setUpdatedAt(Timestamp.from(Instant.now()));
        return creditPayment;
    }
}
