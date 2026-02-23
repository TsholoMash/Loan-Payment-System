package com.example.loanpayment.payment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreatePaymentRequest {

    @NotNull
    private Long loanId;
    @Positive
    private double paymentAmount;

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
