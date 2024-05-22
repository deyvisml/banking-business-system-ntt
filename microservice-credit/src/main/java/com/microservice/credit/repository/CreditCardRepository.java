package com.microservice.credit.repository;

import com.microservice.credit.entity.Credit;
import com.microservice.credit.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    public CreditCard findOneById(Long id);

    public Optional<CreditCard>  findCreditCardByCardNumber(String cardNumber);

    public Optional<CreditCard> findCreditCardByCardNumberAndExpiryMonthAndExpiryYearAndSecurityCode(String cardNumber, Integer expiryMonth, Integer expiryYear, String securityCode);

    @Transactional
    @Modifying(clearAutomatically = true) // fixed update the "context" so updated data is retrived ref:https://stackoverflow.com/a/59269843/23501909
    @Query("UPDATE CreditCard c SET c.debt = :debt WHERE c.id = :id")
    public int updateDebtByCreditId(Long id, float debt);
}
