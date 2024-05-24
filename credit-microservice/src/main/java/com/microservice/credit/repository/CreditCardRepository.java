package com.microservice.credit.repository;

import com.microservice.credit.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing CreditCard entities in the database.
 * 
 * @author Deyvis Mamani Lacuta
 */
@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    /**
     * Finds and returns a credit card entity by its unique identifier.
     *
     * @param id The unique identifier of the credit card to find
     * @return The credit card entity with the specified ID, or null if not found
     */
    public CreditCard findOneById(Long id);

    /**
     * Finds a credit card by its card number.
     *
     * @param cardNumber The card number to search for
     * @return An Optional containing the CreditCard if found, or empty if not found
     */
    public Optional<CreditCard> findCreditCardByCardNumber(String cardNumber);

    /**
     * Finds a credit card by its card number, expiry month, expiry year, and
     * security code.
     *
     * @param cardNumber   The card number of the credit card to find
     * @param expiryMonth  The expiry month of the credit card to find
     * @param expiryYear   The expiry year of the credit card to find
     * @param securityCode The security code of the credit card to find
     * @return An Optional containing the CreditCard if found, or empty if not found
     */
    public Optional<CreditCard> findCreditCardByCardNumberAndExpiryMonthAndExpiryYearAndSecurityCode(String cardNumber,
            Integer expiryMonth, Integer expiryYear, String securityCode);

    /**
     * Updates the debt amount for a credit card with the specified ID.
     *
     * @param id   The ID of the credit card to update
     * @param debt The new debt amount to set
     * @return The number of credit cards updated (should be 1 if successful)
     */
    @Transactional
    @Modifying(clearAutomatically = true) // fixed update the "context" so updated data is retrived
                                          // ref:https://stackoverflow.com/a/59269843/23501909
    @Query("UPDATE CreditCard c SET c.debt = :debt WHERE c.id = :id")
    public int updateDebtByCreditCardId(Long id, float debt);

    /**
     * Finds and returns a list of credit cards associated with the given client ID.
     *
     * @param clientId The ID of the client to search for
     * @return An optional containing the list of credit cards if found, otherwise
     *         empty
     */
    public Optional<List<CreditCard>> findCreditCardsByClientId(Long clientId);
}
