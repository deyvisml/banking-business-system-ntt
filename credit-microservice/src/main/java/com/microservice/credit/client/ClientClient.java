package com.microservice.credit.client;

import com.microservice.credit.dto.ClientDto;
import com.microservice.credit.dto.ClientResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client-microservice", url = "localhost:8091/api/clients")
public interface ClientClient {

    @GetMapping("/{id}")
    public ClientResponseDto findClientById(@PathVariable Long id);
}
