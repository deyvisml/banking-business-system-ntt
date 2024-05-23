package com.microservice.credit.factory;

import com.microservice.credit.entity.CreditCard;
import com.microservice.credit.entity.CreditCardOperation;

import java.sql.Timestamp;
import java.time.Instant;

public class CreditCardOperationFactory {
    public CreditCardOperation createCreditCardOperation(String type, Float amount, CreditCard creditCard) {
        CreditCardOperation creditCardOperation = new CreditCardOperation();
        creditCardOperation.setType(type);
        creditCardOperation.setAmount(amount);
        creditCardOperation.setDebtBefore(creditCard.getDebt());
        if (type.equals("pago"))
            creditCardOperation.setDebtAfter(creditCard.getDebt() - amount);
        else
            creditCardOperation.setDebtAfter(creditCard.getDebt() + amount);
        creditCardOperation.setCreditCard(creditCard);
        creditCardOperation.setCreatedAt(Timestamp.from(Instant.now()));
        creditCardOperation.setUpdatedAt(Timestamp.from(Instant.now()));
        return creditCardOperation;
    }
}
