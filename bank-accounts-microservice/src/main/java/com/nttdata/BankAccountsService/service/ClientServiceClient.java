package com.nttdata.BankAccountsService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class ClientServiceClient {
    @Autowired
    private RestTemplate restTemplate;
    private static final String CLIENT_SERVICE_URL = "http://localhost:8091/api/clients";

    /**
     * Returns the response from the customer microservice API to find the type of a customer by its ID.
     * @param client_id ClientID
     * @return A string representing the type of client or an error message specifying what happened.
     */
    public String getClientTypeById(Integer client_id){
        String url = CLIENT_SERVICE_URL + "/" + client_id + "/type";
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return "Resource not found";
            } else {
                return "HTTP error: " + e.getStatusCode();
            }
        } catch (ResourceAccessException e) {
            return "Network error: " + e.getMessage();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
