package com.microservice.credit.exception;

/**
 * Custom exception class for handling request-related errors.
 * 
 * @author Deyvis Mamani Lacuta
 */
public class RequestException extends RuntimeException {
    /**
     * Constructs a new RequestException with the specified detail message.
     *
     * @param message the detail message
     */
    public RequestException(String message) {
        super(message);
    }
}
