package com.nttdata.apiclients.repository;


import com.nttdata.apiclients.entity.ClientsEntity;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientsRepository extends JpaRepository<ClientsEntity, Integer> {
}
