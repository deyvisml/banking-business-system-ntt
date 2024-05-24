package com.microservice.credit.client;

import com.microservice.credit.dto.ClientDto;
import com.microservice.credit.dto.ClientResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client for interacting with the "client-microservice" to retrieve
 * client information.
 * 
 * @author Deyvis Mamani Lacuta
 */
@FeignClient(name = "client-microservice", url = "localhost:8091/api/clients")
public interface ClientClient {

    /**
     * Retrieves a client by their unique identifier.
     *
     * @param id The unique identifier of the client
     * @return The ClientResponseDto containing the client information
     */
    @GetMapping("/{id}")
    public ClientResponseDto findClientById(@PathVariable Long id);
}
