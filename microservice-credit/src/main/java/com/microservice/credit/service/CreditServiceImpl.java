package com.microservice.credit.service;

import com.microservice.credit.dto.PaymentDebtRequestDto;
import com.microservice.credit.dto.PaymentDebtResponseDto;
import com.microservice.credit.entity.Credit;
import com.microservice.credit.repository.CreditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CreditServiceImpl implements ICreditService {

    private static final Logger log = LoggerFactory.getLogger(CreditServiceImpl.class);
    @Autowired
    private CreditRepository creditRepository;

    @Override
    public List<Credit> findAll() {
        return creditRepository.findAll();
    }

    @Override
    public Credit findCreditById(Long id) {
        return creditRepository.findCreditById(id);
    }

}
