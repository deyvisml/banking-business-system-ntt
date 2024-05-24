package com.microservice.credit.service;

import com.microservice.credit.entity.CreditPayment;
import com.microservice.credit.exception.RequestException;
import com.microservice.credit.repository.CreditPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing credit payments.
 * 
 * @author Deyvis Mamani Lacuta
 */
@Service
public class CreditPaymentServiceImpl implements ICreditPaymentService {

    @Autowired
    private CreditPaymentRepository creditPaymentRepository;

    /**
     * Retrieves all credit payments from the repository.
     *
     * @return A list of all credit payments
     */
    @Override
    public List<CreditPayment> findAll() {
        return creditPaymentRepository.findAll();
    }

    /**
     * Finds a credit payment by its ID.
     *
     * @param id The ID of the credit payment to find
     * @return The credit payment entity if found
     * @throws RequestException if no credit payment is found with the given ID
     */
    @Override
    public CreditPayment findCreditPaymentById(Long id) {
        Optional<CreditPayment> creditPayment = creditPaymentRepository.findOneById(id);

        if (creditPayment.isEmpty())
            throw new RequestException("There is not a credit payment with id: " + id);

        return creditPayment.get();
    }
}
