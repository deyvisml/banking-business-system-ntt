package com.microservice.credit.service;

import com.microservice.credit.dto.*;
import com.microservice.credit.entity.CreditCard;
import com.microservice.credit.entity.CreditCardOperation;

import java.util.List;

public interface ICreditCardService {
    public List<CreditCard> findAll();

    public CreditCard findCreditCardById(Long id);

    public Object storeCreditCard(CreditCardStoreRequestDto creditCardStoreRequestDto);

    public CreditCardOperation makeDebtPayment(PaymentDebtRequestDto paymentDebtRequestDto);

    public CreditCardOperation makeCharge(CreditCardChargeRequestDto creditCardChargeRequestDto);

    public Float getAvailableAmount(AvailableAmountRequestDto availableAmountRequestDto);
}
