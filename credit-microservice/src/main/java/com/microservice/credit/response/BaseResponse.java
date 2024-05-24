package com.microservice.credit.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A generic class representing a base response with status, message, and data.
 *
 * @param <T> The type of data included in the response
 * 
 * @author Deyvis Mamani Lacuta
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    private Boolean status;
    private String message;
    private T data;
}
