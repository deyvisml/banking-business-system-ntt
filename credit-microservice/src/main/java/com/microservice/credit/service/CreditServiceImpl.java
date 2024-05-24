package com.microservice.credit.service;

import com.microservice.credit.client.ClientClient;
import com.microservice.credit.dto.*;
import com.microservice.credit.entity.Credit;
import com.microservice.credit.entity.CreditPayment;
import com.microservice.credit.exception.RequestException;
import com.microservice.credit.factory.CreditFactory;
import com.microservice.credit.factory.CreditPaymentFactory;
import com.microservice.credit.repository.CreditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class that implements the ICreditService interface to manage credit
 * operations.
 * 
 * @author Deyvis Mamani Lacuta
 */
@Service
public class CreditServiceImpl implements ICreditService {

    private static final Logger log = LoggerFactory.getLogger(CreditServiceImpl.class);
    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private ClientClient clientClient;

    /**
     * Retrieves all credits from the database.
     *
     * @return A list of all credits stored in the database
     */
    @Override
    public List<Credit> findAll() {
        return creditRepository.findAll();
    }

    /**
     * Finds a credit by its ID from the list of all credits.
     *
     * @param id The ID of the credit to find
     * @return The credit with the specified ID
     * @throws RequestException if no credit is found with the given ID
     */
    @Override
    public Credit findCreditById(Long id) {
        List<Credit> allCredits = creditRepository.findAll();

        Optional<Credit> creditOptional = allCredits.stream()
                .filter(credit -> credit.getId().equals(id))
                .findFirst();

        if (creditOptional.isEmpty())
            throw new RequestException("There is not a credit with id: " + id);

        return creditOptional.get();
    }

    /**
     * Stores credit information based on the provided CreditStoreRequestDto.
     *
     * @param creditStoreRequestDto The data transfer object containing credit
     *                              information
     * @return The stored Credit entity
     * @throws RequestException if there is an issue with the request
     */
    @Override
    public Credit storeCredit(CreditStoreRequestDto creditStoreRequestDto) {
        Float loanAmount = creditStoreRequestDto.getLoanAmount();
        String startDate = creditStoreRequestDto.getStartDate();
        String endDate = creditStoreRequestDto.getEndDate();
        Float interestRate = creditStoreRequestDto.getInterestRate();
        Long clientId = creditStoreRequestDto.getClientId();

        String clientType = getClientType(clientId);

        if (clientType.equals("personal")) {
            Optional<List<Credit>> optionalCredits = creditRepository.findAllByClientIdAndStatus(clientId, "activo");
            if (optionalCredits.isEmpty())
                throw new RequestException("Failed operation, It was not possible to get the client credtis.");
            else if (!optionalCredits.get().isEmpty())
                throw new RequestException("The client personal already has an active credit.");
        }

        Credit credit = new CreditFactory().createCredit(loanAmount, startDate, endDate, interestRate, clientId);

        return creditRepository.save(credit);
    }

    /**
     * Makes a payment towards a credit debt based on the provided
     * PaymentCreditDebtRequestDto.
     *
     * @param paymentCreditDebtRequestDto The DTO containing payment information
     * @return The CreditPayment object representing the payment made
     * @throws RequestException if the credit is not found, amount exceeds the debt,
     *                          or for any other failed operation
     */
    @Override
    public CreditPayment makeDebtPayment(PaymentCreditDebtRequestDto paymentCreditDebtRequestDto) {
        Long creditId = paymentCreditDebtRequestDto.getId();

        Optional<Credit> creditOptional = creditRepository.findOneById(creditId);

        if (creditOptional.isEmpty())
            throw new RequestException("Failed operation, there is not a Credit with id: " + creditId);

        Credit credit = creditOptional.get();

        if (paymentCreditDebtRequestDto.getAmount() > credit.getAmount() - credit.getAmountPaid())
            throw new RequestException("Failed operation, the amount exceed the debt.");

        Float amount = paymentCreditDebtRequestDto.getAmount();

        CreditPayment creditPayment = new CreditPaymentFactory().createCreditPayment(amount, credit);
        if (credit.getAmountPaid() + amount == credit.getAmount())
            credit.setStatus("pagado");
        credit.setAmountPaid(credit.getAmountPaid() + amount);
        credit.setUpdatedAt(Timestamp.from(Instant.now()));
        credit.getPayments().add(creditPayment);

        creditRepository.save(credit);

        Optional<Credit> updatedCredit = creditRepository.findOneById(credit.getId());

        return creditPayment;
    }

    /**
     * Retrieves a list of credits associated with a specific client ID.
     *
     * @param clientId The ID of the client to find credits for
     * @return A list of credits belonging to the specified client
     */
    @Override
    public List<Credit> findCreditsByClientId(Long clientId) {
        List<Credit> allCredits = creditRepository.findAll();

        return allCredits.stream()
                .filter(credit -> credit.getClientId().equals(clientId))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves the type of client based on the client ID.
     *
     * @param clientId The ID of the client
     * @return The type of the client
     */
    public String getClientType(Long clientId) {
        ClientResponseDto clientResponseDto = clientClient.findClientById(clientId);
        return clientResponseDto.getData().getTypeClient();
    }
}
