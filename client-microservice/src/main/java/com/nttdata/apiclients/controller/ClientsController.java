package com.nttdata.apiclients.controller;

import com.nttdata.apiclients.Service.ClientsService;
import com.nttdata.apiclients.agregates.response.ResponseBase;
import com.nttdata.apiclients.entity.ClientsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientsController {
    @Autowired
    private final ClientsService clientsService;
    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }
    @GetMapping("/{id}")
    public ResponseBase findOne(@PathVariable int id){
        ResponseBase responseBase = clientsService.findOneClients(id);
        return responseBase;
    }
    @GetMapping()
    public ResponseBase findAll(){
        ResponseBase responseBase = clientsService.findAllClients();
        return responseBase;
    }
    @GetMapping("/{id}/type")
    public String findOneType(@PathVariable int id) throws Exception {
        String responseBase = clientsService.findTypeClients(id);
        return responseBase;
    }


}
