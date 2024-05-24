package com.microservice.credit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Credit Card entity with its attributes and relationships.
 *
 * This entity class maps to the "credit_cards" table in the database.
 *
 * Fields:
 * - id: The unique identifier of the credit card.
 * - cardNumber: The number of the credit card.
 * - expiryMonth: The expiration month of the credit card.
 * - expiryYear: The expiration year of the credit card.
 * - securityCode: The security code of the credit card.
 * - limitAmount: The maximum amount that can be spent using the credit card.
 * - debt: The current debt amount on the credit card.
 * - interestRate: The interest rate applied to the debt.
 * - clientId: The ID of the client associated
 * 
 * @author Deyvis Mamani Lacuta
 */
@Data
@Entity
@Builder
@Table(name = "credit_cards")
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "expiry_month")
    private Integer expiryMonth;
    @Column(name = "expiry_year")
    private Integer expiryYear;
    @Column(name = "security_code")
    private String securityCode;
    @Column(name = "limit_amount")
    private float limitAmount;
    private float debt;
    @Column(name = "interest_rate")
    private float interestRate;
    @Column(name = "client_id")
    private Long clientId;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "creditCard", cascade = CascadeType.ALL)
    private List<CreditCardOperation> creditCardOperations;
}
