package com.microservice.credit.service;

import com.microservice.credit.dto.*;
import com.microservice.credit.entity.CreditCard;
import com.microservice.credit.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardServiceImpl implements ICreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public List<CreditCard> findAll()
    {
        return creditCardRepository.findAll();
    }

    public CreditCard findCreditCardById(Long id)
    {
        return creditCardRepository.findOneById(id);
    }

    public PaymentDebtResponseDto makeDebtPayment(PaymentDebtRequestDto paymentDebtRequestDto) {
        String cardNumber = paymentDebtRequestDto.getCardNumber();

        Optional<CreditCard> optionalCreditCard = creditCardRepository.findCreditCardByCardNumber(cardNumber);

        if (optionalCreditCard.isEmpty())
            return new PaymentDebtResponseDto(false, "Operación fallida, el número de tarjeta no existe.", null);

        CreditCard creditCard = optionalCreditCard.get();

        if (paymentDebtRequestDto.getAmount() > creditCard.getDebt() )
            return new PaymentDebtResponseDto(false, "Operación fallida, el monto supera la deuda.", creditCard);

        float newDebt = creditCard.getDebt() - paymentDebtRequestDto.getAmount();
        int numAffectedRecords = creditCardRepository.updateDebtByCreditCardId(creditCard.getId(), newDebt);

        CreditCard updatedCreditCard = creditCardRepository.findOneById(creditCard.getId());

        return new PaymentDebtResponseDto(true, "Operación exitosa, se pagaron S/ " + paymentDebtRequestDto.getAmount(), updatedCreditCard);
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

        if (currentDebt + creditCardChargeRequestDto.getAmount() > currentLimitAmount)
            return new CreditCardChargeResponseDto(false, "Operación fallida, el monto limite de S/ " + currentLimitAmount + " será superado.", creditCard);

        float newDebt = currentDebt + creditCardChargeRequestDto.getAmount();

        int numAffectedRecords = creditCardRepository.updateDebtByCreditCardId(creditCard.getId(), newDebt);

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
}
