package com.microservice.credit.repository;

import com.microservice.credit.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    public Credit findCreditById(Long id);

    public Credit findOneById(Long id);


}
