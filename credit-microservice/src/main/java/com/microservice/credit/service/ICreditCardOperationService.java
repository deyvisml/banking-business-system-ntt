package com.microservice.credit.service;

import com.microservice.credit.dto.*;
import com.microservice.credit.entity.CreditCard;
import com.microservice.credit.entity.CreditCardOperation;

import java.util.List;

public interface ICreditCardOperationService {
    public List<CreditCardOperation> findAll();

    public CreditCardOperation findCreditCardOperationById(Long id);
}
