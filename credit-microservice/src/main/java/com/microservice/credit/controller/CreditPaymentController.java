package com.microservice.credit.controller;

import com.microservice.credit.entity.CreditPayment;
import com.microservice.credit.exception.RequestException;
import com.microservice.credit.response.BaseResponse;
import com.microservice.credit.service.CreditPaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/credit-payments")
public class CreditPaymentController {

    @Autowired
    private CreditPaymentServiceImpl creditPaymentService;

    @GetMapping("")
    public ResponseEntity<BaseResponse<?>> index()
    {
        try {
            List<CreditPayment> creditPayments =creditPaymentService.findAll();
            BaseResponse<List<CreditPayment>> baseResponse = new BaseResponse<>(true, "Credit payments obtained successfully.", creditPayments);
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
            CreditPayment creditPayment = creditPaymentService.findCreditPaymentById(id);
            BaseResponse<CreditPayment> baseResponse = new BaseResponse<>(true, "Credit payment obtained successfully.", creditPayment);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
        catch (RequestException ex)
        {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
