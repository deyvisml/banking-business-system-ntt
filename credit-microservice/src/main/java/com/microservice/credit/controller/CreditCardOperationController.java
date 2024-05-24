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

/**
 * Controller class for handling credit card operations API endpoints.
 * 
 * @author Deyvis Mamani Lacuta
 */
@RestController
@RequestMapping("/api/credit-card-operations")
public class CreditCardOperationController {

    @Autowired
    private CreditCardOperationServiceImpl creditCardOperationService;

    /**
     * Retrieves all credit card operations and returns a response entity with the
     * list of operations.
     *
     * @return ResponseEntity containing a BaseResponse with a list of
     *         CreditCardOperation objects if successful,
     *         or a BaseResponse with an error message if an exception is caught
     */
    @GetMapping("")
    public ResponseEntity<BaseResponse<?>> index() {
        try {
            List<CreditCardOperation> creditCardOperations = creditCardOperationService.findAll();
            BaseResponse<List<CreditCardOperation>> baseResponse = new BaseResponse<>(true,
                    "Credit card operations obtained successfully.", creditCardOperations);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (RequestException ex) {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves a specific credit card operation by its ID.
     *
     * @param id The ID of the credit card operation to retrieve
     * @return ResponseEntity containing the response with the credit card operation
     *         if found, or an error message if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<?>> show(@PathVariable Long id) {
        try {
            CreditCardOperation creditCardOperation = creditCardOperationService.findCreditCardOperationById(id);
            BaseResponse<CreditCardOperation> baseResponse = new BaseResponse<>(true,
                    "Credit card operation obtained successfully.", creditCardOperation);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (RequestException ex) {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
