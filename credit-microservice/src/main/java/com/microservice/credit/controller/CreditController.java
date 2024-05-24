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

/**
 * This class represents the Credit Controller which handles API endpoints
 * related to credits.
 * It provides functionalities to retrieve, create, and manage credits.
 * 
 * @author Deyvis Mamani Lacuta
 */
@RestController
@RequestMapping("/api/credits")
public class CreditController {

    @Autowired
    private CreditServiceImpl creditService;

    /**
     * Retrieves all credits and returns a response entity with the list of credits.
     *
     * @return ResponseEntity containing a BaseResponse with a list of credits if
     *         successful,
     *         or an error message if an exception is caught
     */
    @GetMapping("")
    public ResponseEntity<BaseResponse<?>> index() {
        try {
            List<Credit> credits = creditService.findAll();
            BaseResponse<List<Credit>> baseResponse = new BaseResponse<>(true, "Credits obtained successfully.",
                    credits);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (RequestException ex) {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves a specific credit by its ID.
     *
     * @param id The ID of the credit to retrieve.
     * @return ResponseEntity containing the credit if found, or an error message if
     *         not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<?>> show(@PathVariable Long id) {
        try {
            Credit credit = creditService.findCreditById(id);
            BaseResponse<Credit> baseResponse = new BaseResponse<>(true, "Credit obtained successfully.", credit);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (RequestException ex) {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint to store credit information based on the provided
     * CreditStoreRequestDto.
     *
     * @param creditStoreRequestDto The CreditStoreRequestDto containing credit
     *                              information to be stored
     * @return ResponseEntity with a BaseResponse containing the result of the
     *         credit storage operation
     */
    @PostMapping("")
    public ResponseEntity<BaseResponse<?>> store(@RequestBody CreditStoreRequestDto creditStoreRequestDto) {
        try {
            Credit credit = creditService.storeCredit(creditStoreRequestDto);
            BaseResponse<Credit> baseResponse = new BaseResponse<>(true, "Credit created successfully.", credit);
            return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
        } catch (RequestException ex) {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint for making a debt payment using the provided payment details.
     *
     * @param paymentCreditDebtRequestDto The DTO containing payment details
     * @return ResponseEntity with a success message and the credit payment object
     *         if successful,
     *         or an error message if the payment could not be processed
     */
    @PostMapping("/pay-debt")
    public Object payDebt(@RequestBody PaymentCreditDebtRequestDto paymentCreditDebtRequestDto) {
        try {
            CreditPayment creditPayment = creditService.makeDebtPayment(paymentCreditDebtRequestDto);
            BaseResponse<CreditPayment> baseResponse = new BaseResponse<>(true, "Debt payment made successfully.",
                    creditPayment);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (RequestException ex) {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves a list of credits associated with a specific client ID.
     *
     * @param clientId The unique identifier of the client
     * @return ResponseEntity containing the list of credits if successful, or an
     *         error message if an exception occurs
     */
    @GetMapping("/find-by-client-id/{clientId}")
    public ResponseEntity<BaseResponse<?>> getCreditsByClientId(@PathVariable Long clientId) {
        try {
            List<Credit> credits = creditService.findCreditsByClientId(clientId);
            BaseResponse<List<Credit>> baseResponse = new BaseResponse<>(true, "Credits obtained successfully.",
                    credits);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (RequestException ex) {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
