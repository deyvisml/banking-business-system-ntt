package com.microservice.credit.controller;

import com.microservice.credit.dto.CreditCardChargeRequestDto;
import com.microservice.credit.dto.PaymentCreditDebtRequestDto;
import com.microservice.credit.dto.PaymentDebtRequestDto;
import com.microservice.credit.entity.Credit;
import com.microservice.credit.service.CreditServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
public class CreditController {

    @Autowired
    private CreditServiceImpl creditService;

    @GetMapping("")
    public List<Credit> index()
    {
        return creditService.findAll();
    }

    @GetMapping("/{id}")
    public Credit show(@PathVariable Long id)
    {
        return creditService.findCreditById(id);
    }

    @PostMapping("/pay-debt")
    public Object payDebt( @RequestBody PaymentCreditDebtRequestDto paymentCreditDebtRequestDto ){
        return creditService.makeDebtPayment(paymentCreditDebtRequestDto);
    }
}
