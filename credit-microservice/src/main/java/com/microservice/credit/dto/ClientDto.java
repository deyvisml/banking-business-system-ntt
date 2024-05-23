package com.microservice.credit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private String numDocument;
    private String typeClient;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
}
