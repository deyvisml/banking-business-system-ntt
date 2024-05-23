package com.microservice.credit.service;

import com.microservice.credit.entity.CreditPayment;

import java.util.List;

public interface ICreditPaymentService {
    public List<CreditPayment> findAll();
    public CreditPayment findCreditPaymentById(Long id);
}
