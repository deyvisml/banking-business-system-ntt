package com.example.BankAccountsService.controller;

import com.example.BankAccountsService.DTO.BankAccountDTO;
import com.example.BankAccountsService.DTO.OperationDTO;
import com.example.BankAccountsService.exception.RequestException;
import com.example.BankAccountsService.model.BankAccount;
import com.example.BankAccountsService.model.BankAccountLog;
import com.example.BankAccountsService.response.BaseResponse;
import com.example.BankAccountsService.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/bank_accounts")
public class BankAccountController {
    @Autowired
    BankAccountService bankAccountService;

    /**
     * Endpoint to create a bank account for a specific client.
     *
     * @param bankAccountDTO The DTO object containing the information of the bank account to be created.
     * @param clientId The ID of the client for whom the bank account will be created.
     * @return ResponseEntity<?> A ResponseEntity containing the response of the bank account creation process.
     */
    @PostMapping("/create/{client_id}")
    public ResponseEntity<?> create(@RequestBody BankAccountDTO bankAccountDTO, @PathVariable("client_id") Integer clientId){
        try{
            BankAccount bankAccount = bankAccountService.createAccount(clientId , bankAccountDTO);
            BaseResponse<BankAccount> response = new BaseResponse<>("Bank account created successfully.", true , bankAccount);
            return new ResponseEntity<>(response , HttpStatus.OK);
        }catch (RequestException ex){
            BaseResponse<Void> response = new BaseResponse<>(ex.getMessage() , false , null);
            return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
        }
    }
    /**
     * Endpoint to delete a bank account by its ID.
     *
     * @param bank_account_id The ID of the bank account to be deleted.
     * @return ResponseEntity<?> A ResponseEntity containing the response of the bank account deletion process.
     */
    @DeleteMapping("/delete/{bank_account_id}")
    public ResponseEntity<?> deleteAccount(@PathVariable("bank_account_id") Integer bank_account_id){
        try{
            Boolean delete_Success = bankAccountService.deleteAccount(bank_account_id);
            BaseResponse<Void> response = new BaseResponse<>("Account deleted successfully." , delete_Success ,null);
            return new ResponseEntity<>(response , HttpStatus.OK);
        }catch(RequestException ex){
            BaseResponse<Void> response = new BaseResponse<>(ex.getMessage() , false , null);
            return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
        }
    }

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<?> getAllAccounts(){
        try{
            List<BankAccount> bankAccounts = bankAccountService.getAllAccounts();
            BaseResponse<List<BankAccount>> response = new BaseResponse<>("Bank accounts successfully obtained." , true , bankAccounts);
            return new ResponseEntity<>(response , HttpStatus.OK);
        }catch (RequestException ex){
            BaseResponse<Void> response = new BaseResponse<>(ex.getMessage() , false , null);
            return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{client_id}")
    public ResponseEntity<?> clientAccounts(@PathVariable("client_id") Integer clientID){
        try{
            Set<BankAccount> bankAccounts = bankAccountService.findAccountsByClientId(clientID);
            BaseResponse<Set<BankAccount>> response = new BaseResponse<>("Client accounts successfully obtained." , true , bankAccounts);
            return new ResponseEntity<>(response , HttpStatus.OK);
        }catch (RequestException ex){
            BaseResponse<Void> response = new BaseResponse<>(ex.getMessage() , false , null);
            return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/operation/{bank_account_id}")
    public ResponseEntity<?> updateBalance(@PathVariable("bank_account_id") Integer accountId , @RequestBody OperationDTO operationDTO){
        try{
            BankAccountLog operation = bankAccountService.updateBalance(accountId , operationDTO);
            BaseResponse<BankAccountLog> response = new BaseResponse<>("Operation carried out successfully.", true  , operation);
            return new ResponseEntity<>(response , HttpStatus.OK);
        }catch(RequestException ex){
            BaseResponse<Void> response = new BaseResponse<>(ex.getMessage() , false , null);
            return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/holder/add/{client_id}/{bank_account_id}")
    public ResponseEntity<?> addHolder(@PathVariable("client_id") Integer clientId, @PathVariable("bank_account_id") Integer bank_account_id){
        try{
            BankAccount bankAccount = bankAccountService.addHolder(clientId , bank_account_id);
            BaseResponse<BankAccount> response = new BaseResponse<>("Holder assigned successfully." , true, bankAccount);
            return new ResponseEntity<>(response , HttpStatus.OK);
        }catch (RequestException ex){
            BaseResponse<Void> response = new BaseResponse<>(ex.getMessage() , false , null);
            return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signatory/add/{client_id}/{bank_account_id}")
    public ResponseEntity<?> addSignatory(@PathVariable("client_id") Integer clientId, @PathVariable("bank_account_id") Integer bank_account_id){
        try{
            BankAccount bankAccount = bankAccountService.addSignatory(clientId , bank_account_id);
            BaseResponse<BankAccount> response = new BaseResponse<>("Signartory assigned successfully." , true , bankAccount);
            return new ResponseEntity<>(response , HttpStatus.OK);
        }catch(RequestException ex){
            BaseResponse<Void> response = new BaseResponse<>(ex.getMessage() , false , null);
            return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
        }
    }
}
