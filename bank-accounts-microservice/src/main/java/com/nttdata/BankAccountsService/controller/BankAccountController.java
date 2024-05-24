package com.nttdata.BankAccountsService.controller;

import com.nttdata.BankAccountsService.DTO.BankAccountDTO;
import com.nttdata.BankAccountsService.DTO.OperationDTO;
import com.nttdata.BankAccountsService.exception.RequestException;
import com.nttdata.BankAccountsService.model.BankAccount;
import com.nttdata.BankAccountsService.model.BankAccountLog;
import com.nttdata.BankAccountsService.response.BaseResponse;
import com.nttdata.BankAccountsService.service.BankAccountService;
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
     * @return ResponseEntity<?> A ResponseEntity containing the response of the bank account creation process, or an error message if an exception occurs.
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
     * @return ResponseEntity<?> A ResponseEntity containing the response of the bank account deletion process, or an error message if an exception occurs.
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
     * Returns all accounts registered in the database.
     * @return A ResponseEntity that contains the response from the process of getting all the accounts,
     *          or an error message if an exception occurs.
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

    /**
     * Returns all accounts for which the client appears as a holder or signatory
     * @param clientID The id of the client for which the accounts will be obtained.
     * @return A ResponseEntity that contains the response state of the server and the accounts corresponding to the client,
     *         or an error message if an exception occurs.
     */
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
    /**
     * Updates the balance of a bank account by performing a specified operation.
     *
     * @param accountId The ID of the bank account on which the operation will be performed.
     * @param operationDTO The details of the operation to be performed, including the type of operation and the amount.
     * @return A ResponseEntity containing a BaseResponse with a success message and the details of the operation performed,
     *         or an error message if an exception occurs.
     */
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
    /**
     * Assigns a holder to a bank account.
     *
     * @param clientId The ID of the client to be assigned as a holder.
     * @param bank_account_id The ID of the bank account to which the holder will be assigned.
     * @return A ResponseEntity containing a BaseResponse with a success message and the updated BankAccount,
     *         or an error message if an exception occurs.
     */
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
    /**
     * Assigns a signatory to a bank account.
     *
     * @param clientId The ID of the client to be assigned as a signatory.
     * @param bank_account_id The ID of the bank account to which the signatory will be assigned.
     * @return A ResponseEntity containing a BaseResponse with a success message and the updated BankAccount,
     *         or an error message if an exception occurs.
     */
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
