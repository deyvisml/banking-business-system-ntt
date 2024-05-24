package com.microservice.credit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Represents a credit card operation entity that is stored in the database.
 *
 * This entity contains information about a credit card transaction operation,
 * including the type of operation, amount, debt before and after the operation,
 * creation and update timestamps, and the associated credit card.
 *
 * Fields:
 * - id: The unique identifier of the credit card operation.
 * - type: The type of operation performed (e.g., purchase, refund).
 * - amount: The amount involved in the operation.
 * - debtBefore: The debt amount before the operation.
 * - debtAfter: The debt amount after the operation.
 * - createdAt: The timestamp when the operation was created.
 * - updatedAt: The timestamp when the operation was last updated.
 * 
 * @author Deyvis Mamani Lacuta
 */
@Data
@Entity
@Builder
@Table(name = "credit_card_operations")
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private Float amount;
    @Column(name = "debt_before")
    private Float debtBefore;
    @Column(name = "debt_after")
    private Float debtAfter;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "credit_card_id", nullable = false)
    private CreditCard creditCard;
}
