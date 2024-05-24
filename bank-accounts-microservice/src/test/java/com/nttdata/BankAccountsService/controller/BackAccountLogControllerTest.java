package com.nttdata.BankAccountsService.controller;

import com.nttdata.BankAccountsService.exception.RequestException;
import com.nttdata.BankAccountsService.model.BankAccountLog;
import com.nttdata.BankAccountsService.response.BaseResponse;
import com.nttdata.BankAccountsService.service.BankAccountLogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BackAccountLogControllerTest {
    @InjectMocks
    BackAccountLogController backAccountLogController;
    @Mock
    BankAccountLogService bankAccountLogService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetAllTransactions_Success() {
        List<BankAccountLog> expectedLogs = new ArrayList<>();
        expectedLogs.add(new BankAccountLog());
        expectedLogs.add(new BankAccountLog());

        when(bankAccountLogService.getAllOperations()).thenReturn(expectedLogs);

        ResponseEntity<?> responseEntity = backAccountLogController.getAllTransactions();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        BaseResponse<?> responseBody = (BaseResponse<?>) responseEntity.getBody();
        assertEquals("Logs obtained successfully.", responseBody.getMessage());
        assertEquals(true, responseBody.getSuccess());
        assertEquals(expectedLogs, responseBody.getData());
    }

    @Test
    void testGetAllTransactions_Exception() {
        String errorMessage = "An error occurred";
        when(bankAccountLogService.getAllOperations()).thenThrow(new RequestException(errorMessage));

        ResponseEntity<?> responseEntity = backAccountLogController.getAllTransactions();

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        BaseResponse<?> responseBody = (BaseResponse<?>) responseEntity.getBody();
        assertEquals(errorMessage, responseBody.getMessage());
        assertEquals(false, responseBody.getSuccess());
        assertNull(responseBody.getData());
    }

    @Test
    void testGetAllAccountTransactions_Success() {
        Integer bankAccountId = 1;
        List<BankAccountLog> expectedLogs = new ArrayList<>();
        expectedLogs.add(new BankAccountLog());
        expectedLogs.add(new BankAccountLog());

        when(bankAccountLogService.getAllAccountOperations(bankAccountId)).thenReturn(expectedLogs);

        ResponseEntity<?> responseEntity = backAccountLogController.getAllAccountTransactions(bankAccountId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        BaseResponse<?> responseBody = (BaseResponse<?>) responseEntity.getBody();
        assertEquals("Account logs obtained successfully.", responseBody.getMessage());
        assertEquals(true, responseBody.getSuccess());
        assertEquals(expectedLogs, responseBody.getData());
    }

    @Test
    void testGetAllAccountTransactions_Exception() {
        Integer bankAccountId = 1;
        String errorMessage = "An error occurred";
        when(bankAccountLogService.getAllAccountOperations(bankAccountId)).thenThrow(new RequestException(errorMessage));

        ResponseEntity<?> responseEntity = backAccountLogController.getAllAccountTransactions(bankAccountId);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        BaseResponse<?> responseBody = (BaseResponse<?>) responseEntity.getBody();
        assertEquals(errorMessage, responseBody.getMessage());
        assertEquals(false, responseBody.getSuccess());
        assertEquals(null, responseBody.getData());
    }
}