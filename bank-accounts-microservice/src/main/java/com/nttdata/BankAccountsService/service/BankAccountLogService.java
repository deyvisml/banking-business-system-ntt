package com.nttdata.BankAccountsService.service;

import com.nttdata.BankAccountsService.model.BankAccountLog;
import com.nttdata.BankAccountsService.repository.BankAccountLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BankAccountLogService {
    @Autowired
    private BankAccountLogRepository bankAccountLogRepository;

    /**
     * Get all history transactions.
     * @return A list of al bankAccountLogs.
     */
    public List<BankAccountLog> getAllOperations(){
        return bankAccountLogRepository.findAll();
    }

    /**
     * Get all history transactions from a specfic account.
     * @param bank_account_id Account ID
     * @return A list containing all the logs corresponding to the account.
     */
    public List<BankAccountLog> getAllAccountOperations(Integer bank_account_id){
        return bankAccountLogRepository.findByBankAccountId((bank_account_id));
    }

}
