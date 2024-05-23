package com.microservice.credit.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDto {
    private int code;
    private String message;
    private ClientDto data;
}
