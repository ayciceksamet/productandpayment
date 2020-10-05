package com.iyzico.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StockNotAvailableException extends RuntimeException {
    public StockNotAvailableException(String message) {
        super(message + " product not available in the system.");
    }
}

