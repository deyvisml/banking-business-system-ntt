package com.microservice.credit.service;

import com.microservice.credit.entity.CreditCardOperation;
import com.microservice.credit.exception.RequestException;
import com.microservice.credit.repository.CreditCardOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Credit Card Operations, implementing
 * ICreditCardOperationService.
 * This class provides methods to interact with CreditCardOperation entities.
 * 
 * @author Deyvis Mamani Lacuta
 */
@Service
public class CreditCardOperationServiceImpl implements ICreditCardOperationService {

    @Autowired
    private CreditCardOperationRepository creditCardOperationRepository;

    /**
     * Retrieves all credit card operations from the repository.
     *
     * @return A list of all credit card operations
     */
    @Override
    public List<CreditCardOperation> findAll() {
        return creditCardOperationRepository.findAll();
    }

    /**
     * Finds a credit card operation by its ID.
     *
     * @param id The ID of the credit card operation to find
     * @return The credit card operation with the specified ID
     * @throws RequestException if no credit card operation is found for the given
     *                          ID
     */
    @Override
    public CreditCardOperation findCreditCardOperationById(Long id) {
        Optional<CreditCardOperation> creditCardOperation = creditCardOperationRepository.findOneById(id);

        if (creditCardOperation.isEmpty()) {
            throw new RequestException("There is not a credit card operation fot the id: " + id);
        }

        return creditCardOperation.get();
    }
}
