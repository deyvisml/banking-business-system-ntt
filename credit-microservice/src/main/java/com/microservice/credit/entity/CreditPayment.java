package com.microservice.credit.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Represents a credit payment entity that is stored in the "credit_payments"
 * table.
 *
 * This entity class defines the structure of the credit payment object,
 * including its attributes and relationships.
 *
 * @param id        The unique identifier of the credit payment.
 * @param amount    The amount of credit payment.
 * @param createdAt The timestamp when the credit payment was created.
 * @param updatedAt The timestamp when the credit payment was last updated.
 * @param credit    The credit associated with this payment.
 * 
 * @author Deyvis Mamani Lacuta
 */
@Data
@Entity
@Builder
@Table(name = "credit_payments")
@AllArgsConstructor
@NoArgsConstructor
public class CreditPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float amount;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "credit_id", nullable = false)
    private Credit credit;
}
