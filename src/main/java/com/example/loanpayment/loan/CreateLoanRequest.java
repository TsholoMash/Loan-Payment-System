package com.example.loanpayment.loan;

import jakarta.validation.constraints.Positive;

public class CreateLoanRequest {

    @Positive
    private double loanAmount;
    @Positive
    private int term;

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }
}
