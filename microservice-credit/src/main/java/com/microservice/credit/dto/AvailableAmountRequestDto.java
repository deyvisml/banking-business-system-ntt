package com.microservice.credit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableAmountRequestDto {
    String cardNumber;
    Integer expiryMonth;
    Integer expiryYear;
    String securityCode;
}
