package com.microservice.credit.controller;

import com.microservice.credit.service.CreditCardOperationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/credit-card-operations")
public class CreditCardOperationController {

    @Autowired
    private CreditCardOperationServiceImpl creditCardOperationService;

    @GetMapping("")
    public Object index()
    {
        return creditCardOperationService.findAll();
    }

    @GetMapping("/{id}")
    public Object show(@PathVariable Long id)
    {
        return creditCardOperationService.findCreditCardOperationById(id);
    }
}
