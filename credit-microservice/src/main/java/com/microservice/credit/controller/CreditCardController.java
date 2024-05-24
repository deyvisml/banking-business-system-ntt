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

/**
 * This class represents the Credit Card Controller which handles various HTTP
 * requests related to credit cards.
 * It contains methods for retrieving, creating, updating, and deleting credit
 * card information.
 * 
 * @author Deyvis Mamani Lacuta
 */
@RestController
@RequestMapping("/api/credit-cards")
public class CreditCardController {

    @Autowired
    private CreditCardServiceImpl creditCardService;

    /**
     * Retrieves all credit cards and returns a response entity with the list of
     * credit cards.
     *
     * @return ResponseEntity containing a BaseResponse with a list of CreditCard
     *         objects if successful,
     *         or an error message if an exception is caught
     */
    @GetMapping("")
    public ResponseEntity<BaseResponse<?>> index() {
        try {
            List<CreditCard> creditCards = creditCardService.findAll();
            BaseResponse<List<CreditCard>> baseResponse = new BaseResponse<>(true,
                    "Credit cards obtained successfully.", creditCards);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (RequestException ex) {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves a credit card by its ID and returns a response entity with the
     * credit card information.
     *
     * @param id The ID of the credit card to retrieve
     * @return ResponseEntity containing a BaseResponse with the credit card
     *         information if found, or an error message if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<?>> show(@PathVariable Long id) {
        try {
            CreditCard creditCard = creditCardService.findCreditCardById(id);
            BaseResponse<CreditCard> baseResponse = new BaseResponse<>(true, "Credit card obtained successfully.",
                    creditCard);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (RequestException ex) {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint to store a credit card using the provided request data.
     *
     * @param creditCardStoreRequestDto The request data containing the credit card
     *                                  details
     * @return ResponseEntity with a BaseResponse containing the result of the
     *         operation
     */
    @PostMapping("")
    public ResponseEntity<BaseResponse<?>> store(@RequestBody CreditCardStoreRequestDto creditCardStoreRequestDto) {
        try {
            CreditCard creditCard = creditCardService.storeCreditCard(creditCardStoreRequestDto);
            BaseResponse<CreditCard> baseResponse = new BaseResponse<>(true, "Credit card created successfully.",
                    creditCard);
            return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
        } catch (RequestException ex) {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint for making a debt payment using a credit card.
     *
     * @param paymentDebtRequestDto The payment debt request data transfer object
     *                              containing payment details
     * @return ResponseEntity with a BaseResponse containing the result of the debt
     *         payment operation
     */
    @PostMapping("/pay-debt")
    public ResponseEntity<BaseResponse<?>> payDebt(@RequestBody PaymentDebtRequestDto paymentDebtRequestDto) {
        try {
            CreditCardOperation creditCardOperation = creditCardService.makeDebtPayment(paymentDebtRequestDto);
            BaseResponse<CreditCardOperation> baseResponse = new BaseResponse<>(true, "Debt payment made successfully.",
                    creditCardOperation);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (RequestException ex) {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint for making a credit card charge.
     *
     * @param creditCardChargeRequestDto The request body containing information for
     *                                   the credit card charge
     * @return ResponseEntity with a BaseResponse containing the result of the
     *         credit card charge operation
     */
    @PostMapping("/charge")
    public ResponseEntity<BaseResponse<?>> creditCardCharge(
            @RequestBody CreditCardChargeRequestDto creditCardChargeRequestDto) {
        try {
            CreditCardOperation creditCardOperation = creditCardService.makeCharge(creditCardChargeRequestDto);
            BaseResponse<CreditCardOperation> baseResponse = new BaseResponse<>(true, "Charge made successfully.",
                    creditCardOperation);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (RequestException ex) {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves the available amount for a credit card based on the provided
     * request data.
     *
     * @param availableAmountRequestDto The request data containing information
     *                                  needed to retrieve the available amount
     * @return ResponseEntity containing the response with the available amount or
     *         an error message
     */
    @PostMapping("/available-amount")
    public ResponseEntity<BaseResponse<?>> getAvailableAmount(
            @RequestBody AvailableAmountRequestDto availableAmountRequestDto) {
        try {
            Float availableAmount = creditCardService.getAvailableAmount(availableAmountRequestDto);
            BaseResponse<Float> baseResponse = new BaseResponse<>(true, "Available amount obtained successfully.",
                    availableAmount);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (RequestException ex) {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, "", null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves a list of credit cards associated with a specific client ID.
     *
     * @param clientId The unique identifier of the client
     * @return ResponseEntity containing the list of credit cards if successful, or
     *         an error message if an exception occurs
     */
    @GetMapping("/find-by-client-id/{clientId}")
    public ResponseEntity<BaseResponse<?>> getCreditCardsByClientId(@PathVariable Long clientId) {
        try {
            List<CreditCard> creditCards = creditCardService.findCreditCardsByClientId(clientId);
            BaseResponse<List<CreditCard>> baseResponse = new BaseResponse<>(true,
                    "Credit cards obtained successfully.", creditCards);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (RequestException ex) {
            BaseResponse<Void> baseResponse = new BaseResponse<>(false, ex.getMessage(), null);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
