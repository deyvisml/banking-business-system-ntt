package com.microservice.credit.service;

import com.microservice.credit.client.ClientClient;
import com.microservice.credit.dto.*;
import com.microservice.credit.entity.CreditCard;
import com.microservice.credit.entity.CreditCardOperation;
import com.microservice.credit.factory.CreditCardFactory;
import com.microservice.credit.factory.CreditCardOperationFactory;
import com.microservice.credit.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class CreditCardServiceImpl implements ICreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private ClientClient clientClient;

    public List<CreditCard> findAll()
    {
        return creditCardRepository.findAll();
    }

    public CreditCard findCreditCardById(Long id)
    {
        return creditCardRepository.findOneById(id);
    }

    @Override
    public Object storeCreditCard(CreditCardStoreRequestDto creditCardStoreRequestDto) {
        Integer expirationMonth = creditCardStoreRequestDto.getExpiryMonth();
        Integer expirationYear = creditCardStoreRequestDto.getExpiryYear();
        Float limitAmount = creditCardStoreRequestDto.getLimitAmount();
        Float interestRate = creditCardStoreRequestDto.getInterestRate();
        Long clientId = creditCardStoreRequestDto.getClientId();

        if (getClientById(clientId) == null)
            return null; // the client does not exists

        CreditCard creditCard = new CreditCardFactory().createCreditCard(expirationMonth, expirationYear, limitAmount, interestRate, clientId);
        return creditCardRepository.save(creditCard);
    }

    public PaymentDebtResponseDto makeDebtPayment(PaymentDebtRequestDto paymentDebtRequestDto) {
        String cardNumber = paymentDebtRequestDto.getCardNumber();

        Optional<CreditCard> optionalCreditCard = creditCardRepository.findCreditCardByCardNumber(cardNumber);

        if (optionalCreditCard.isEmpty())
            return new PaymentDebtResponseDto(false, "Operación fallida, el número de tarjeta no existe.", null);

        CreditCard creditCard = optionalCreditCard.get();

        Float amount = paymentDebtRequestDto.getAmount();

        if (amount > creditCard.getDebt() )
            return new PaymentDebtResponseDto(false, "Operación fallida, el monto supera la deuda.", creditCard);

        float newDebt = creditCard.getDebt() - amount;

        CreditCardOperation creditCardOperation = new CreditCardOperationFactory().createCreditCardOperation("pago", amount, creditCard);
        creditCard.setDebt(newDebt);
        creditCard.setUpdatedAt(Timestamp.from(Instant.now()));
        creditCard.getCreditCardOperations().add(creditCardOperation);
        creditCardRepository.save(creditCard);

        CreditCard updatedCreditCard = creditCardRepository.findOneById(creditCard.getId());

        return new PaymentDebtResponseDto(true, "Operación exitosa, se pagaron S/ " + amount, updatedCreditCard);
    }

    public CreditCardChargeResponseDto makeCharge(CreditCardChargeRequestDto creditCardChargeRequestDto)
    {
        String cardNumber = creditCardChargeRequestDto.getCardNumber();
        Integer expiryMonth = creditCardChargeRequestDto.getExpiryMonth();
        Integer expiryYear = creditCardChargeRequestDto.getExpiryYear();
        String securityCode = creditCardChargeRequestDto.getSecurityCode();
        Optional<CreditCard> optionalCreditCard = creditCardRepository.findCreditCardByCardNumberAndExpiryMonthAndExpiryYearAndSecurityCode(cardNumber, expiryMonth, expiryYear, securityCode);

        if (optionalCreditCard.isEmpty())
            return new CreditCardChargeResponseDto(false, "Operación fallida, datos de tarjeta incorrectos.", null);

        CreditCard creditCard = optionalCreditCard.get();

        float currentLimitAmount = creditCard.getLimitAmount();
        Float currentDebt = creditCard.getDebt();

        Float amount = creditCardChargeRequestDto.getAmount();

        if (currentDebt + amount > currentLimitAmount)
            return new CreditCardChargeResponseDto(false, "Operación fallida, el monto limite de S/ " + currentLimitAmount + " será superado.", creditCard);

        float newDebt = currentDebt + amount;

        CreditCardOperation creditCardOperation = new CreditCardOperationFactory().createCreditCardOperation("cargo", amount, creditCard);
        creditCard.setDebt(newDebt);
        creditCard.setUpdatedAt(Timestamp.from(Instant.now()));
        creditCard.getCreditCardOperations().add(creditCardOperation);
        creditCardRepository.save(creditCard);

        CreditCard updatedCreditCard = creditCardRepository.findOneById(creditCard.getId());

        return new CreditCardChargeResponseDto(true, "Operación exitosa, se realizó el cargo a su tarjeta de credito.", updatedCreditCard);
    }

    @Override
    public AvailableAmountResponseDto getAvailableAmount(AvailableAmountRequestDto availableAmountRequestDto) {
        String cardNumber = availableAmountRequestDto.getCardNumber();
        Integer expiryMonth = availableAmountRequestDto.getExpiryMonth();
        Integer expiryYear = availableAmountRequestDto.getExpiryYear();
        String securityCode = availableAmountRequestDto.getSecurityCode();

        Optional<CreditCard> optionalCreditCard = creditCardRepository.findCreditCardByCardNumberAndExpiryMonthAndExpiryYearAndSecurityCode(cardNumber, expiryMonth, expiryYear, securityCode);

        if (optionalCreditCard.isEmpty())
            return new AvailableAmountResponseDto(false, "Operación fallida, datos de tarjeta incorrectos.", null, null);

        CreditCard creditCard = optionalCreditCard.get();

        float availableAmount = creditCard.getLimitAmount() - creditCard.getDebt();

        return new AvailableAmountResponseDto(true, "Operación exitosa, saldo disponible obtenido.", creditCard.getCardNumber(), availableAmount);
    }

    private ClientDto getClientById(Long id)
    {
        ClientResponseDto clientResponseDto = clientClient.findClientById(id);
        return clientResponseDto.getData();
    }
}
