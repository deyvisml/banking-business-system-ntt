package com.microservice.credit.factory;

import com.microservice.credit.entity.CreditCard;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * A factory class for creating CreditCard objects with specified attributes.
 * 
 * @author Deyvis Mamani Lacuta
 */
public class CreditCardFactory {
    /**
     * Creates a new CreditCard object with the provided details.
     *
     * @param expirationMonth The expiration month of the credit card
     * @param expirationYear  The expiration year of the credit card
     * @param limitAmount     The credit limit amount
     * @param interestRate    The interest rate for the credit card
     * @param clientId        The ID of the client associated with the credit card
     * @return A new CreditCard object with the specified details
     */
    public CreditCard createCreditCard(Integer expirationMonth, Integer expirationYear, Float limitAmount,
            Float interestRate, Long clientId) {
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

    /**
     * Generates a random string of numbers with the specified length.
     *
     * @param length The length of the random string to generate
     * @return A random string of numbers with the specified length
     */
    private String generateRandomStringNumber(int length) {
        Random random = new Random();

        return random.ints(48, 58) // ASCII numbers from 0 to 9
                .limit(length)
                .mapToObj(i -> (char) i)
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
