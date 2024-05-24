package com.microservice.credit.controller;

import com.microservice.credit.entity.CreditCardOperation;
import com.microservice.credit.exception.RequestException;
import com.microservice.credit.response.BaseResponse;
import com.microservice.credit.service.CreditCardOperationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/credit-card-operations")
public class CreditCardOperationController {

    @Autowired
    private CreditCardOperationServiceImpl creditCardOperationService;

    @GetMapping("")
    public ResponseEntity<BaseResponse<?>> index()
    {
        try {
            List<CreditCardOperation> creditCardOperations = creditCardOperationService.findAll();
            BaseResponse<List<CreditCardOperation>> baseResponse = new BaseResponse<>(true, "Credit card operations obtained successfully.", creditCardOperations);
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
            CreditCardOperation creditCardOperation = creditCardOperationService.findCreditCardOperationById(id);
            BaseResponse<CreditCardOperation> baseResponse = new BaseResponse<>(true, "Credit card operation obtained successfully.", creditCardOperation);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
        catch (RequestException ex)
        {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
