package com.microservice.credit.repository;

import com.microservice.credit.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {

    public Optional<Credit> findOneById(Long id);

    @Transactional
    @Modifying(clearAutomatically = true) // fixed update the "context" so updated data is retrived ref:https://stackoverflow.com/a/59269843/23501909
    @Query("UPDATE Credit c SET c.amountPaid = :amountPaid WHERE c.id = :id")
    public int updateAmountPaidByCreditId(Long id, float amountPaid);
}
