package com.microservice.credit.controller;

import com.microservice.credit.dto.CreditCardChargeRequestDto;
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
    CreditServiceImpl creditService;

    @GetMapping("")
    public List<Credit> index()
    {
        List<Credit> credits = creditService.findAll();

        return credits;
    }

    @GetMapping("/{id}")
    public Credit show(@PathVariable Long id)
    {
        Credit credit = creditService.findCreditById(id);

        return credit;
    }
}
