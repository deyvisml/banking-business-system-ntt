package com.microservice.credit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object (DTO) representing a client.
 *
 * This class encapsulates client information including their document number,
 * type, first name,
 * last name, email, and telephone number.
 *
 * The class is annotated with Lombok annotations for generating boilerplate
 * code such as getters,
 * setters, constructors, and a builder pattern.
 *
 * @param numDocument The document number of the client
 * @param typeClient  The type of client
 * @param firstName   The first name of the client
 * @param lastName    The last name of the client
 * @param email       The email address of the client
 * @param telephone   The telephone number of the client
 * 
 * @author Deyvis Mamani Lacuta
 */
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
