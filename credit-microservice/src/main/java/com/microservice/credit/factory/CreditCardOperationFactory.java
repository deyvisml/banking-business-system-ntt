package com.microservice.credit.factory;

import com.microservice.credit.entity.CreditCard;
import com.microservice.credit.entity.CreditCardOperation;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * Factory class for creating CreditCardOperation objects based on the provided
 * parameters.
 * 
 * @author Deyvis Mamani Lacuta
 */
public class CreditCardOperationFactory {
    /**
     * Creates a credit card operation based on the provided parameters.
     *
     * @param type       The type of operation (e.g., "pago" for payment)
     * @param amount     The amount involved in the operation
     * @param creditCard The credit card associated with the operation
     * @return The created CreditCardOperation object
     */
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
