package com.example.BankAccountsService.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BaseResponse <T>{
    private String message;
    private Boolean success;
    private T data;
}
