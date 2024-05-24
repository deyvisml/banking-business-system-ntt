package com.nttdata.apiclients.Service.impl;

import com.nttdata.apiclients.Service.ClientsService;
import com.nttdata.apiclients.agregates.response.ResponseBase;
import com.nttdata.apiclients.constants.Constants;
import com.nttdata.apiclients.entity.ClientsEntity;
import com.nttdata.apiclients.repository.ClientsRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ClientsServiceImpl implements ClientsService {
    private final ClientsRepository clientsRepository;

    public ClientsServiceImpl(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }
    @Override
    public ResponseBase findOneClients(Integer id) {
        Optional clients = clientsRepository.findById(id);
        if(clients.isPresent()){
            return new ResponseBase(Constants.CODE_SUCCES, Constants.MESS_SUCCESS,clients);
        }
        return new ResponseBase(Constants.CODE_SUCCES,Constants.MESS_NON_DATA, Optional.empty());
    }
    @Override
    public ResponseBase findAllClients() {
        Optional allClients = Optional.of(clientsRepository.findAll());
        if(allClients.isPresent()){
            return new ResponseBase(Constants.CODE_SUCCES, Constants.MESS_SUCCESS,allClients);
        }
        return new ResponseBase(Constants.CODE_SUCCES,Constants.MESS_ZERO_ROWS, Optional.empty());
    }
    public String findTypeClients(Integer id) throws Exception {
        Optional<ClientsEntity> client = clientsRepository.findById(id);
        return client.map(campo -> {
            return campo.getType();
        }).orElse("No existe el tipo de cliente");
    }



}
