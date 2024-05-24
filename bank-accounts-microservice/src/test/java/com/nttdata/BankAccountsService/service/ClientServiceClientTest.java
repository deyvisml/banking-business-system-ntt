package com.nttdata.BankAccountsService.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;
class ClientServiceClientTest {
    @InjectMocks
    private ClientServiceClient clientServiceClient;
    @Mock
    private RestTemplate restTemplate = new RestTemplate();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private final String CLIENT_SERVICE_URL = "http://localhost:8095/clients";

    @Test
    void getClientTypeById_success() {
        int client_id = 1;
        String url = CLIENT_SERVICE_URL + "/" + client_id + "/type";
        when(restTemplate.getForObject(url , String.class)).thenReturn("personal");
        assertEquals("personal" , clientServiceClient.getClientTypeById(client_id));
    }

    @Test
    void testGetClientTypeById_HTTP_Error(){
        Integer clientId = 1;
        String expectedUrl = CLIENT_SERVICE_URL + "/" + clientId + "/type";
        when(restTemplate.getForObject(expectedUrl , String.class)).thenThrow(new HttpClientErrorException(REQUEST_TIMEOUT));
        String actualResponse = clientServiceClient.getClientTypeById(clientId);

        assertEquals("HTTP error: " + REQUEST_TIMEOUT , actualResponse);
    }
    @Test
    void testGetClientTypeById_NotFound() {
        Integer clientId = 1;
        String expectedUrl = CLIENT_SERVICE_URL + "/" + clientId + "/type";

        when(restTemplate.getForObject(expectedUrl, String.class)).thenThrow(new HttpClientErrorException(NOT_FOUND));

        String actualResponse = clientServiceClient.getClientTypeById(clientId);

        assertEquals("Resource not found", actualResponse);
    }

    @Test
    void testGetClientTypeById_NetworkError() {
        Integer clientId = 1;
        String expectedUrl = CLIENT_SERVICE_URL + "/" + clientId + "/type";

        when(restTemplate.getForObject(expectedUrl, String.class)).thenThrow(new ResourceAccessException("Network error"));

        String actualResponse = clientServiceClient.getClientTypeById(clientId);

        assertEquals("Network error: Network error", actualResponse);
    }

    @Test
    void testGetClientTypeById_OtherError() {
        Integer clientId = 1;
        String expectedUrl = CLIENT_SERVICE_URL + "/" + clientId + "/type";

        when(restTemplate.getForObject(expectedUrl, String.class)).thenThrow(new RuntimeException("Unknown error"));

        String actualResponse = clientServiceClient.getClientTypeById(clientId);

        assertEquals("Error: Unknown error", actualResponse);
    }
}