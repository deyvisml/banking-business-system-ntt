package com.microservice.credit.controller;

import com.microservice.credit.dto.CreditCardChargeRequestDto;
import com.microservice.credit.dto.PaymentDebtRequestDto;
import com.microservice.credit.entity.CreditCard;
import com.microservice.credit.service.CreditCardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credit-cards")
public class CreditCardController {

    @Autowired
    CreditCardServiceImpl creditCardService;

    @GetMapping("")
    public List<CreditCard> index()
    {
        List<CreditCard> creditCards = creditCardService.findAll();

        return creditCards;
    }

    @GetMapping("/{id}")
    public CreditCard show(@PathVariable Long id)
    {
        CreditCard creditCard = creditCardService.findCreditCardById(id);

        return creditCard;
    }

    @PostMapping("/pay-debt")
    public Object payDebt( @RequestBody PaymentDebtRequestDto paymentDebtRequestDto ){
        return creditCardService.makeDebtPayment(paymentDebtRequestDto);
    }

    @PostMapping("/credit-card-charge")
    public Object creditCardCharge(@RequestBody CreditCardChargeRequestDto creditCardChargeRequestDto )
    {
        return true;
    }
}
