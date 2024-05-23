package com.microservice.credit.service;

import com.microservice.credit.dto.*;
import com.microservice.credit.entity.CreditCard;

import java.util.List;

public interface ICreditCardService {
    public List<CreditCard> findAll();

    public CreditCard findCreditCardById(Long id);

    public Object storeCreditCard(CreditCardStoreRequestDto creditCardStoreRequestDto);

    public PaymentDebtResponseDto makeDebtPayment(PaymentDebtRequestDto paymentDebtRequestDto);

    public CreditCardChargeResponseDto makeCharge(CreditCardChargeRequestDto creditCardChargeRequestDto);

    public AvailableAmountResponseDto getAvailableAmount(AvailableAmountRequestDto availableAmountRequestDto);
}
