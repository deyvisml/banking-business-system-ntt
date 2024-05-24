package com.nttdata.apiclients.Service;

import com.nttdata.apiclients.agregates.response.ResponseBase;
import com.nttdata.apiclients.entity.ClientsEntity;

import java.util.List;

public interface ClientsService {
    ResponseBase findOneClients(Integer id);
    ResponseBase findAllClients();
    String findTypeClients(Integer id) throws Exception;
}
