package com.nttdata.BankAccountsService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
@Data
@Entity
@Table(name = "bank_account_logs")
public class BankAccountLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "operation_type")
    private String operationType;
    @Column(name = "operation_amount")
    private Float operationAmount;
    @Column(name = "balance_before_operation")
    private Float balanceBeforeOperation;
    @Column(name = "balance_after_operation")
    private Float balanceAfterOperation;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "bank_account_id", nullable = false)
    private BankAccount bankAccount;

    private Timestamp createdAt;
    private Timestamp updatedAt;

}
