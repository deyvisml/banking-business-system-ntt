package com.microservice.credit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditStoreRequestDto {
    private Float loanAmount;
    private String startDate;
    private String endDate;
    private Float interestRate;
    private Long clientId;
}
