package com.microservice.credit.service;

import com.microservice.credit.dto.PaymentDebtRequestDto;
import com.microservice.credit.dto.PaymentDebtResponseDto;
import com.microservice.credit.entity.Credit;
import com.microservice.credit.entity.CreditCard;
import com.microservice.credit.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardServiceImpl {

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
        int num_records_affected = creditCardRepository.updateDebtByCreditId(creditCard.getId(), newDebt);

        CreditCard updatedCreditCard = creditCardRepository.findOneById(creditCard.getId());

        return new PaymentDebtResponseDto(true, "Operación exitosa, se pagaron S/ " + paymentDebtRequestDto.getAmount(), updatedCreditCard);
    }
}
