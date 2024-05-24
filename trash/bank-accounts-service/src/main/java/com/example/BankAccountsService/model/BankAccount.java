package com.example.BankAccountsService.model;
import javax.persistence.*;
import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
@Data
@Entity
@EqualsAndHashCode(exclude = {"holders", "signatories", "logs"})
@Table(name = "bank_accounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "balance")
    private Float balance;
    @Column(name = "monthly_operations_executed")
    private Integer monthlyOperationsExecuted;
    @Column(name = "monthly_operations_limit")
    private Integer monthlyOperationsLimit;
    @Column(name = "monthly_maintenance_fee")
    private Float monthlyMaintenanceFee;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL , orphanRemoval = true)
    private Set<Holder> holders = new HashSet<>();

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL , orphanRemoval = true)
    private Set<Signatory> signatories = new HashSet<>();

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BankAccountLog> logs = new HashSet<>();

    public void updateHolders(Holder holder){
        this.holders.add(holder);
    }

    public void updateSignatories(Signatory signatory){
        this.signatories.add(signatory);
    }
}
