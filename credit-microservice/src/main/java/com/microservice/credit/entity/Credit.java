package com.microservice.credit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a credit entity with details such as loan amount, amount, amount
 * paid, start and end dates,
 * interest rate, client ID, status, creation and update timestamps, and a list
 * of credit payments.
 *
 * This entity is mapped to the "credits" table in the database.
 *
 * @param id           The unique identifier of the credit.
 * @param loanAmount   The amount of the loan.
 * @param amount       The total amount.
 * @param amountPaid   The amount that has been paid.
 * @param startDate    The start date of the credit.
 * @param endDate      The end date of the credit.
 * @param interestRate The interest rate of the credit.
 * @param clientId     The ID of the client associated with the credit
 * 
 * @author Deyvis Mamani Lacuta
 */
@Data
@Entity
@Builder
@Table(name = "credits")
@AllArgsConstructor
@NoArgsConstructor
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "loan_amount")
    private Float loanAmount;
    private Float amount;
    @Column(name = "amount_paid")
    private Float amountPaid;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "interest_rate")
    private float interestRate;
    @Column(name = "client_id")
    private Long clientId;
    private String status;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CreditPayment> payments = new ArrayList<>();
}
