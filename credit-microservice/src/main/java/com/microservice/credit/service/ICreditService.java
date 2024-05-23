package com.microservice.credit.service;

import com.microservice.credit.dto.PaymentCreditDebtRequestDto;
import com.microservice.credit.dto.PaymentCreditDebtResponseDto;
import com.microservice.credit.dto.PaymentDebtRequestDto;
import com.microservice.credit.dto.PaymentDebtResponseDto;
import com.microservice.credit.entity.Credit;

import java.util.List;

public interface ICreditService {
    public List<Credit> findAll();

    public Credit findCreditById(Long id);

    public PaymentCreditDebtResponseDto makeDebtPayment(PaymentCreditDebtRequestDto paymentCreditDebtRequestDto);
}