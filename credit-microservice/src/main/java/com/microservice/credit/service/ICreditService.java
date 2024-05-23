package com.microservice.credit.service;

import com.microservice.credit.dto.*;
import com.microservice.credit.entity.Credit;

import java.util.List;

public interface ICreditService {
    public List<Credit> findAll();

    public Credit findCreditById(Long id);

    public Object storeCredit(CreditStoreRequestDto creditStoreRequestDto);

    public PaymentCreditDebtResponseDto makeDebtPayment(PaymentCreditDebtRequestDto paymentCreditDebtRequestDto);
}
