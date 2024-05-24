package com.microservice.credit.controller;

import com.microservice.credit.dto.*;
import com.microservice.credit.entity.CreditCard;
import com.microservice.credit.entity.CreditCardOperation;
import com.microservice.credit.exception.RequestException;
import com.microservice.credit.response.BaseResponse;
import com.microservice.credit.service.CreditCardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credit-cards")
public class CreditCardController {

    @Autowired
    private CreditCardServiceImpl creditCardService;

    @GetMapping("")
    public ResponseEntity<BaseResponse<?>> index()
    {
        try {
            List<CreditCard> creditCards = creditCardService.findAll();
            BaseResponse<List<CreditCard>> baseResponse = new BaseResponse<>(true, "Credit cards obtained successfully.", creditCards);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
        catch (RequestException ex)
        {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<?>> show(@PathVariable Long id)
    {
        try {
            CreditCard creditCard = creditCardService.findCreditCardById(id);
            BaseResponse<CreditCard> baseResponse = new BaseResponse<>(true, "Credit card obtained successfully.", creditCard);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
        catch (RequestException ex)
        {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse<?>> store(@RequestBody CreditCardStoreRequestDto creditCardStoreRequestDto)
    {
        try {
            CreditCard creditCard = creditCardService.storeCreditCard(creditCardStoreRequestDto);
            BaseResponse<CreditCard> baseResponse = new BaseResponse<>(true, "Credit card created successfully.", creditCard);
            return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
        }
        catch (RequestException ex)
        {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/pay-debt")
    public ResponseEntity<BaseResponse<?>> payDebt( @RequestBody PaymentDebtRequestDto paymentDebtRequestDto ){
        try {
            CreditCardOperation creditCardOperation = creditCardService.makeDebtPayment(paymentDebtRequestDto);
            BaseResponse<CreditCardOperation> baseResponse = new BaseResponse<>(true, "Debt payment made successfully.", creditCardOperation);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
        catch (RequestException ex)
        {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/charge")
    public ResponseEntity<BaseResponse<?>> creditCardCharge(@RequestBody CreditCardChargeRequestDto creditCardChargeRequestDto )
    {
        try{
            CreditCardOperation creditCardOperation = creditCardService.makeCharge(creditCardChargeRequestDto);
            BaseResponse<CreditCardOperation> baseResponse = new BaseResponse<>(true, "Charge made successfully.", creditCardOperation);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
        catch (RequestException ex)
        {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/available-amount")
    public ResponseEntity<BaseResponse<?>> getAvailableAmount(@RequestBody AvailableAmountRequestDto availableAmountRequestDto )
    {
        try {
            Float availableAmount = creditCardService.getAvailableAmount(availableAmountRequestDto);
            BaseResponse<Float> baseResponse = new BaseResponse<>(true, "Available amount obtained successfully.", availableAmount);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
        catch (RequestException ex)
        {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, "", null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
