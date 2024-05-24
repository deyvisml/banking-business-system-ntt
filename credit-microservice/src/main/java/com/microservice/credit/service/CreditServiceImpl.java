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

@Service
public class CreditServiceImpl implements ICreditService {

    private static final Logger log = LoggerFactory.getLogger(CreditServiceImpl.class);
    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private ClientClient clientClient;

    @Override
    public List<Credit> findAll() {
        return creditRepository.findAll();
    }

    @Override
    public Credit findCreditById(Long id) {
        Optional<Credit> creditOptional = creditRepository.findById(id);

        return creditOptional.orElse(null);
    }

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
                throw new RequestException("Failed operation, It was not posible to get the client credtis.");
            else if (!optionalCredits.get().isEmpty())
                throw new RequestException("The client personal already has an active credit.");
        }

        Credit credit = new CreditFactory().createCredit(loanAmount, startDate, endDate, interestRate, clientId);

        return creditRepository.save(credit);
    }

    @Override
    public CreditPayment makeDebtPayment(PaymentCreditDebtRequestDto paymentCreditDebtRequestDto) {
        Long creditId = paymentCreditDebtRequestDto.getId();

        Optional<Credit> creditOptional = creditRepository.findOneById(creditId);

        if (creditOptional.isEmpty())
            throw new RequestException("Failed operation, there is not a Credit with id: " + creditId );

        Credit credit = creditOptional.get();

        if (paymentCreditDebtRequestDto.getAmount() > credit.getAmount() - credit.getAmountPaid())
            throw new RequestException("Failed operation, the amount exceed the debt.");

        Float amount = paymentCreditDebtRequestDto.getAmount();

        CreditPayment creditPayment = new CreditPaymentFactory().createCreditPayment(amount, credit);
        if ( credit.getAmountPaid() + amount == credit.getAmount())
           credit.setStatus("pagado");
        credit.setAmountPaid(credit.getAmountPaid() + amount);
        credit.setUpdatedAt(Timestamp.from(Instant.now()));
        credit.getPayments().add(creditPayment);

        creditRepository.save(credit);

        Optional<Credit> updatedCredit = creditRepository.findOneById(credit.getId());

        return creditPayment;
    }

    public String getClientType(Long clientId)
    {
        ClientResponseDto clientResponseDto = clientClient.findClientById(clientId);
        return clientResponseDto.getData().getTypeClient();
    }
}
