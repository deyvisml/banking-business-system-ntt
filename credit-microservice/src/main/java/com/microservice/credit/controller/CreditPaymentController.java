package com.microservice.credit.controller;

import com.microservice.credit.service.CreditPaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/credit-payments")
public class CreditPaymentController {

    @Autowired
    private CreditPaymentServiceImpl creditPaymentService;

    @GetMapping("")
    public Object index()
    {
        return creditPaymentService.findAll();
    }

    @GetMapping("/{id}")
    public Object show(@PathVariable Long id)
    {
        return creditPaymentService.findCreditPaymentById(id);
    }
}
