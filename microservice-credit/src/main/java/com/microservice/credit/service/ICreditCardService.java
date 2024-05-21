package com.microservice.credit.service;

import com.microservice.credit.dto.PaymentDebtRequestDto;
import com.microservice.credit.dto.PaymentDebtResponseDto;
import com.microservice.credit.entity.CreditCard;

import java.util.List;

public interface ICreditCardService {
    public List<CreditCard> findAll();

    public CreditCard findCreditCardById(Long id);

    public CreditCard findCreditCardByCardNumber(String cardNumber);

    public PaymentDebtResponseDto makeDebtPayment(Long creditId, PaymentDebtRequestDto paymentDebtRequestDto);
}
