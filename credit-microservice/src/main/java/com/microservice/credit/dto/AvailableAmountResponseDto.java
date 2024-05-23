package com.microservice.credit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableAmountResponseDto {
    private boolean status;
    private String message;
    String cardNumber;
    Float availableAmount;
}
