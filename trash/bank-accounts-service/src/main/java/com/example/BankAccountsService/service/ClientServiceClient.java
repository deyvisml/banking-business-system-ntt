package com.example.BankAccountsService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientServiceClient {
    @Autowired
    private RestTemplate restTemplate;
    private static final String CLIENT_SERVICE_URL = "http://localhost:8095/clients";
    public String getClientTypeById(Integer client_id){
        String url = CLIENT_SERVICE_URL + "/" + client_id + "/type";
        return restTemplate.getForObject(url, String.class);
    }
}
