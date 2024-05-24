package com.microservice.credit.controller;

import com.microservice.credit.entity.CreditCard;
import com.microservice.credit.response.BaseResponse;
import com.microservice.credit.service.CreditCardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.microservice.credit.exception.RequestException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CreditCardControllerTest {
    @InjectMocks
    CreditCardController creditCardController;
    @Mock
    CreditCardServiceImpl creditCardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIndex_Success() throws Exception {
        CreditCard card1 = new CreditCard();
        CreditCard card2 = new CreditCard();
        List<CreditCard> creditCards = Arrays.asList(card1, card2);
        when(creditCardService.findAll()).thenReturn(creditCards);

        ResponseEntity<BaseResponse<?>> response = creditCardController.index();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        BaseResponse<?> baseResponse = response.getBody();
        assertNotNull(baseResponse);
        assertTrue(baseResponse.getStatus());
        assertEquals("Credit cards obtained successfully.", baseResponse.getMessage());
        assertEquals(creditCards, baseResponse.getData());
    }

    @Test
    void testIndex_RequestException() throws Exception {
        String errorMessage = "Error occurred";
        when(creditCardService.findAll()).thenThrow(new RequestException(errorMessage));

        ResponseEntity<BaseResponse<?>> response = creditCardController.index();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        BaseResponse<?> baseResponse = response.getBody();
        assertNotNull(baseResponse);
        assertFalse(baseResponse.getStatus());
        assertEquals(errorMessage, baseResponse.getMessage());
        assertNull(baseResponse.getData());
    }

}