package com.nttdata.BankAccountsService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDTO {
    private String type;
    private float balance;
    private Integer monthlyOperationsLimit;
    private Float monthlyMaintenanceFee;
}
