package com.example.loanpayment.exception;

public class OverPaymentException extends RuntimeException {

    public OverPaymentException(String message) {
        super(message);
    }

}
