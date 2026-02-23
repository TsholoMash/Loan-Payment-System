package com.example.loanpayment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OverPaymentException extends RuntimeException {

    public OverPaymentException(String message) {
        super(message);
    }

}
