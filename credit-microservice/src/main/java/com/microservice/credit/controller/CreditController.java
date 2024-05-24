package com.microservice.credit.controller;

import com.microservice.credit.dto.CreditCardChargeRequestDto;
import com.microservice.credit.dto.CreditStoreRequestDto;
import com.microservice.credit.dto.PaymentCreditDebtRequestDto;
import com.microservice.credit.dto.PaymentDebtRequestDto;
import com.microservice.credit.entity.Credit;
import com.microservice.credit.entity.CreditPayment;
import com.microservice.credit.exception.RequestException;
import com.microservice.credit.response.BaseResponse;
import com.microservice.credit.service.CreditServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
public class CreditController {

    @Autowired
    private CreditServiceImpl creditService;

    @GetMapping("")
    public ResponseEntity<BaseResponse<?>> index()
    {
        try {
            List<Credit> credits = creditService.findAll();
            BaseResponse<List<Credit>> baseResponse = new BaseResponse<>(true, "Credits obtained successfully.", credits);
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
            Credit credit = creditService.findCreditById(id);
            BaseResponse<Credit> baseResponse = new BaseResponse<>(true, "Credit obtained successfully.", credit);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
        catch (RequestException ex)
        {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse<?>> store(@RequestBody CreditStoreRequestDto creditStoreRequestDto)
    {
        try {
            Credit credit = creditService.storeCredit(creditStoreRequestDto);
            BaseResponse<Credit> baseResponse = new BaseResponse<>(true, "Credit created successfully.", credit);
            return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
        }
        catch (RequestException ex)
        {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/pay-debt")
    public Object payDebt( @RequestBody PaymentCreditDebtRequestDto paymentCreditDebtRequestDto ){
        try {
            CreditPayment creditPayment = creditService.makeDebtPayment(paymentCreditDebtRequestDto);
            BaseResponse<CreditPayment> baseResponse = new BaseResponse<>(true, "Debt payment made successfully.", creditPayment);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
        catch (RequestException ex)
        {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find-by-client-id/{clientId}")
    public ResponseEntity<BaseResponse<?>> getCreditsByClientId(@PathVariable Long clientId)
    {
        try {
            List<Credit> credits = creditService.findCreditsByClientId(clientId);
            BaseResponse<List<Credit>> baseResponse = new BaseResponse<>(true, "Credits obtained successfully.", credits);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
        catch (RequestException ex)
        {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
