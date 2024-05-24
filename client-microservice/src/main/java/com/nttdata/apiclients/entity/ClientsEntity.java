package com.nttdata.apiclients.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name = "Clients")
public class ClientsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "num_document",length = 15, nullable = false)
    private String numDocument;
    @Column(name = "type", length = 100, nullable = false)
    private String type;
    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;
    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "telephone", length = 15, nullable = false)
    private String telephone;
}
