package com.microservice.credit.dto;

import lombok.*;

/**
 * Data transfer object representing a client response.
 *
 * This class includes a response code, message, and client data.
 *
 * @param code    The response code
 * @param message The response message
 * @param data    The client data
 * 
 * @author Deyvis Mamani Lacuta
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDto {
    private int code;
    private String message;
    private ClientDto data;
}
