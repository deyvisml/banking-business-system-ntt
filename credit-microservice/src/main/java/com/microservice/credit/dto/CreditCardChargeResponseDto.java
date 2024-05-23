package com.microservice.credit.dto;

import com.microservice.credit.entity.CreditCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardChargeResponseDto {
    private Boolean status;
    private String message;
    private CreditCard creditCard;
}
