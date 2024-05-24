package com.nttdata.BankAccountsService.controller;

import com.nttdata.BankAccountsService.exception.RequestException;
import com.nttdata.BankAccountsService.model.BankAccountLog;
import com.nttdata.BankAccountsService.response.BaseResponse;
import com.nttdata.BankAccountsService.service.BankAccountLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bank_account_logs")
public class BackAccountLogController {
    @Autowired
    BankAccountLogService bankAccountLogService;

    @GetMapping()
    public ResponseEntity<?> getAllTransactions(){
        try{
            List<BankAccountLog> bankAccounts = bankAccountLogService.getAllOperations();
            BaseResponse<List<BankAccountLog>> response = new BaseResponse<>("Logs obtained successfully." , true, bankAccounts);
            return  new ResponseEntity<>(response , HttpStatus.OK);
        }catch(RequestException ex){
            BaseResponse<Void> response = new BaseResponse<>(ex.getMessage() , false , null);
            return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{bank_account_id}")
    public ResponseEntity<?> getAllAccountTransactions(@PathVariable("bank_account_id") Integer bank_account_id){
        try{
            List<BankAccountLog> bankAccountLogs = bankAccountLogService.getAllAccountOperations(bank_account_id);
            BaseResponse<List<BankAccountLog>> response = new BaseResponse<>("Account logs obtained successfully." , true , bankAccountLogs);
            return new ResponseEntity<>(response , HttpStatus.OK);
        }catch(RequestException ex){
            BaseResponse<Void> response = new BaseResponse<>(ex.getMessage(), false, null);
            return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
        }
    }
}
