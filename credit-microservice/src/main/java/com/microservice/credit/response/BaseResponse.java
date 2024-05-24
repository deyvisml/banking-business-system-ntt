package com.microservice.credit.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse <T> {
    private Boolean status;
    private String message;
    private T data;
}
