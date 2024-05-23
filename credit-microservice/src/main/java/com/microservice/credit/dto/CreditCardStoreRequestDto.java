package com.microservice.credit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardStoreRequestDto {
    private Integer expiryMonth;
    private Integer expiryYear;
    private Float limitAmount;
    private Float interestRate;
    private Long clientId;
}
