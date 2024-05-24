package com.nttdata.BankAccountsService.service;

import com.nttdata.BankAccountsService.model.BankAccountLog;
import com.nttdata.BankAccountsService.repository.BankAccountLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BankAccountLogServiceTest {

    @InjectMocks
    private BankAccountLogService bankAccountLogService;
    @Mock
    private BankAccountLogRepository bankAccountLogRepository;
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllOperations() {
        // Arrange
        List<BankAccountLog> expectedLogs = new ArrayList<>();
        expectedLogs.add(new BankAccountLog());
        expectedLogs.add(new BankAccountLog());

        when(bankAccountLogRepository.findAll()).thenReturn(expectedLogs);

        List<BankAccountLog> actualLogs = bankAccountLogService.getAllOperations();

        assertEquals(expectedLogs, actualLogs);
    }

    @Test
    void testGetAllAccountOperations() {
        Integer bankAccountId = 1;
        List<BankAccountLog> expectedLogs = new ArrayList<>();
        expectedLogs.add(new BankAccountLog());
        expectedLogs.add(new BankAccountLog());

        when(bankAccountLogRepository.findByBankAccountId(bankAccountId)).thenReturn(expectedLogs);

        List<BankAccountLog> actualLogs = bankAccountLogService.getAllAccountOperations(bankAccountId);

        assertEquals(expectedLogs, actualLogs);
    }

}