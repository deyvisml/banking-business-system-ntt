package com.microservice.credit.factory;

import com.microservice.credit.entity.CreditCard;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;
import java.util.stream.Collectors;

public class CreditCardFactory {
    public CreditCard createCreditCard(Integer expirationMonth, Integer expirationYear, Float limitAmount, Float interestRate, Long clientId) {
        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber(generateRandomStringNumber(14));
        creditCard.setExpiryMonth(expirationMonth);
        creditCard.setExpiryYear(expirationYear);
        creditCard.setSecurityCode(generateRandomStringNumber(3));
        creditCard.setLimitAmount(limitAmount);
        creditCard.setDebt(limitAmount * interestRate / 100);
        creditCard.setInterestRate(interestRate);
        creditCard.setClientId(clientId);
        creditCard.setCreatedAt(Timestamp.from(Instant.now()));
        creditCard.setUpdatedAt(Timestamp.from(Instant.now()));
        return creditCard;
    }

    private String generateRandomStringNumber(int length) {
        Random random = new Random();

        return random.ints(48, 58) // ASCII numbers from 0 to 9
                .limit(length)
                .mapToObj(i -> (char) i)
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
