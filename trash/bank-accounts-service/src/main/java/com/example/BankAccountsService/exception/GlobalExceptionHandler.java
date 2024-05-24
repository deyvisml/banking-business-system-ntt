package com.example.BankAccountsService.exception;

import com.example.BankAccountsService.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RequestException.class)
    public ResponseEntity<BaseResponse<Void>> handleException(RequestException ex){
        BaseResponse<Void> response = new BaseResponse<>(ex.getMessage() , false , null);
        return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
    }
}
