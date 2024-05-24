package com.microservice.credit.service;

import com.microservice.credit.client.ClientClient;
import com.microservice.credit.dto.*;
import com.microservice.credit.entity.CreditCard;
import com.microservice.credit.entity.CreditCardOperation;
import com.microservice.credit.exception.RequestException;
import com.microservice.credit.factory.CreditCardFactory;
import com.microservice.credit.factory.CreditCardOperationFactory;
import com.microservice.credit.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class that implements the ICreditCardService interface to manage
 * credit card operations.
 * 
 * @author Deyvis Mamani Lacuta
 */
@Service
public class CreditCardServiceImpl implements ICreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private ClientClient clientClient;

    /**
     * Retrieves all credit cards from the database.
     *
     * @return A list of all credit cards stored in the database
     */
    public List<CreditCard> findAll() {
        return creditCardRepository.findAll();
    }

    /**
     * Finds a credit card by its ID from the list of all credit cards.
     *
     * @param id The ID of the credit card to find
     * @return The credit card with the specified ID
     * @throws RequestException if no credit card with the given ID is found
     */
    public CreditCard findCreditCardById(Long id) {
        List<CreditCard> allCreditCards = creditCardRepository.findAll();

        Optional<CreditCard> creditCardOptional = allCreditCards.stream()
                .filter(creditCard -> creditCard.getId().equals(id))
                .findFirst();

        if (creditCardOptional.isEmpty())
            throw new RequestException("There is no a credit card with id: " + id);

        return creditCardOptional.get();
    }

    /**
     * Stores a credit card based on the provided CreditCardStoreRequestDto.
     *
     * @param creditCardStoreRequestDto The request containing credit card details
     * @return The stored credit card entity
     * @throws RequestException if the client does not exist
     */
    @Override
    public CreditCard storeCreditCard(CreditCardStoreRequestDto creditCardStoreRequestDto) {
        Integer expirationMonth = creditCardStoreRequestDto.getExpiryMonth();
        Integer expirationYear = creditCardStoreRequestDto.getExpiryYear();
        Float limitAmount = creditCardStoreRequestDto.getLimitAmount();
        Float interestRate = creditCardStoreRequestDto.getInterestRate();
        Long clientId = creditCardStoreRequestDto.getClientId();

        if (getClientById(clientId) == null)
            throw new RequestException("The client does not exists.");

        CreditCard creditCard = new CreditCardFactory().createCreditCard(expirationMonth, expirationYear, limitAmount,
                interestRate, clientId);
        return creditCardRepository.save(creditCard);
    }

    /**
     * Makes a debt payment using the provided PaymentDebtRequestDto.
     *
     * @param paymentDebtRequestDto The PaymentDebtRequestDto containing payment
     *                              information
     * @return The CreditCardOperation representing the payment made
     * @throws RequestException if the credit card number does not exist or the
     *                          amount exceeds the debt
     */
    public CreditCardOperation makeDebtPayment(PaymentDebtRequestDto paymentDebtRequestDto) {
        String cardNumber = paymentDebtRequestDto.getCardNumber();

        Optional<CreditCard> optionalCreditCard = creditCardRepository.findCreditCardByCardNumber(cardNumber);

        if (optionalCreditCard.isEmpty())
            throw new RequestException("Failed operaton, the credit card number does not exists.");

        CreditCard creditCard = optionalCreditCard.get();

        Float amount = paymentDebtRequestDto.getAmount();

        if (amount > creditCard.getDebt())
            throw new RequestException("Failed operation, the amount exceed the debt.");

        float newDebt = creditCard.getDebt() - amount;

        CreditCardOperation creditCardOperation = new CreditCardOperationFactory().createCreditCardOperation("pago",
                amount, creditCard);
        creditCard.setDebt(newDebt);
        creditCard.setUpdatedAt(Timestamp.from(Instant.now()));
        creditCard.getCreditCardOperations().add(creditCardOperation);
        creditCardRepository.save(creditCard);

        CreditCard updatedCreditCard = creditCardRepository.findOneById(creditCard.getId());

        return creditCardOperation;
    }

    /**
     * Makes a charge operation using the provided credit card charge request data.
     *
     * @param creditCardChargeRequestDto The credit card charge request data
     * @return The credit card operation created for the charge
     * @throws RequestException if the credit card data is incorrect or the limit
     *                          amount will be exceeded
     */
    public CreditCardOperation makeCharge(CreditCardChargeRequestDto creditCardChargeRequestDto) {
        String cardNumber = creditCardChargeRequestDto.getCardNumber();
        Integer expiryMonth = creditCardChargeRequestDto.getExpiryMonth();
        Integer expiryYear = creditCardChargeRequestDto.getExpiryYear();
        String securityCode = creditCardChargeRequestDto.getSecurityCode();
        Optional<CreditCard> optionalCreditCard = creditCardRepository
                .findCreditCardByCardNumberAndExpiryMonthAndExpiryYearAndSecurityCode(cardNumber, expiryMonth,
                        expiryYear, securityCode);

        if (optionalCreditCard.isEmpty())
            throw new RequestException("Credit card data is incorrect.");

        CreditCard creditCard = optionalCreditCard.get();

        float currentLimitAmount = creditCard.getLimitAmount();
        Float currentDebt = creditCard.getDebt();

        Float amount = creditCardChargeRequestDto.getAmount();

        if (currentDebt + amount > currentLimitAmount)
            throw new RequestException("Failed operation, the limit amount will be exceeded");

        float newDebt = currentDebt + amount;

        CreditCardOperation creditCardOperation = new CreditCardOperationFactory().createCreditCardOperation("cargo",
                amount, creditCard);
        creditCard.setDebt(newDebt);
        creditCard.setUpdatedAt(Timestamp.from(Instant.now()));
        creditCard.getCreditCardOperations().add(creditCardOperation);
        creditCardRepository.save(creditCard);

        CreditCard updatedCreditCard = creditCardRepository.findOneById(creditCard.getId());

        return creditCardOperation;
    }

    /**
     * Retrieves the available amount for a given credit card based on the request
     * details.
     *
     * @param availableAmountRequestDto The request containing the card details
     * @return The available amount for the credit card
     * @throws RequestException if the credit card does not exist
     */
    @Override
    public Float getAvailableAmount(AvailableAmountRequestDto availableAmountRequestDto) {
        String cardNumber = availableAmountRequestDto.getCardNumber();
        Integer expiryMonth = availableAmountRequestDto.getExpiryMonth();
        Integer expiryYear = availableAmountRequestDto.getExpiryYear();
        String securityCode = availableAmountRequestDto.getSecurityCode();

        Optional<CreditCard> optionalCreditCard = creditCardRepository
                .findCreditCardByCardNumberAndExpiryMonthAndExpiryYearAndSecurityCode(cardNumber, expiryMonth,
                        expiryYear, securityCode);

        if (optionalCreditCard.isEmpty())
            throw new RequestException("The credit card does not exists.");

        CreditCard creditCard = optionalCreditCard.get();

        return creditCard.getLimitAmount() - creditCard.getDebt();
    }

    /**
     * Retrieves a list of credit cards associated with a specific client ID.
     *
     * @param clientId The ID of the client to find credit cards for
     * @return A list of credit cards belonging to the specified client
     */
    @Override
    public List<CreditCard> findCreditCardsByClientId(Long clientId) {
        List<CreditCard> allCreditCards = creditCardRepository.findAll();

        return allCreditCards.stream()
                .filter(creditCard -> creditCard.getClientId().equals(clientId))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a client by their ID from the client service.
     *
     * @param id The ID of the client to retrieve
     * @return The client DTO corresponding to the given ID
     */
    private ClientDto getClientById(Long id) {
        ClientResponseDto clientResponseDto = clientClient.findClientById(id);
        return clientResponseDto.getData();
    }
}
