package com.microservice.credit.dto;

import com.microservice.credit.entity.Credit;
import com.microservice.credit.entity.CreditCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCreditDebtResponseDto {
    private boolean status;
    private String message;
    private Credit credit;
}
