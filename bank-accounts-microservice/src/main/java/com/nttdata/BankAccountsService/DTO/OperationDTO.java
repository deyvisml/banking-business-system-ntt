package com.nttdata.BankAccountsService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationDTO {
    private String type;
    private float amount;
    private Integer client_id;
}
