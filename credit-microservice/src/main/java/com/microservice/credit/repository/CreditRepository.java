package com.microservice.credit.repository;

import com.microservice.credit.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Credit entities in the database.
 * 
 * @author Deyvis Mamani Lacuta
 */
@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {

    /**
     * Retrieves a Credit entity by its unique identifier.
     *
     * @param id The unique identifier of the Credit entity to find
     * @return An Optional containing the Credit entity if found, or empty if not
     *         found
     */
    public Optional<Credit> findOneById(Long id);

    /**
     * Updates the amount paid for a credit entity by its ID.
     *
     * @param id         The ID of the credit entity to update
     * @param amountPaid The new amount to set as paid
     * @return The number of entities updated (should be 1 if successful)
     */
    @Transactional
    @Modifying(clearAutomatically = true) // fixed update the "context" so updated data is retrived
                                          // ref:https://stackoverflow.com/a/59269843/23501909
    @Query("UPDATE Credit c SET c.amountPaid = :amountPaid WHERE c.id = :id")
    public int updateAmountPaidByCreditId(Long id, float amountPaid);

    /**
     * Retrieves a list of credits based on the client ID and status.
     *
     * @param clientId The ID of the client
     * @param status   The status of the credits to filter by
     * @return An optional containing the list of credits if found, otherwise empty
     */
    public Optional<List<Credit>> findAllByClientIdAndStatus(Long clientId, String status);

    /**
     * Finds a list of credits by the client ID.
     *
     * @param clientId The ID of the client to search for credits.
     * @return An optional containing the list of credits if found, otherwise empty.
     */
    public Optional<List<Credit>> findCreditByClientId(Long clientId);
}
