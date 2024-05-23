package com.microservice.credit.factory;

import com.microservice.credit.entity.Credit;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;

public class CreditFactory {
    public Credit createCredit(Float loanAmount, String startDate, String endDate, Float interestRate, Long clientId) {
        Credit credit = new Credit();
        credit.setLoanAmount(loanAmount);
        credit.setAmount(loanAmount + loanAmount*interestRate / 100);
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
