package com.example.loanpayment.loan;


import jakarta.persistence.*;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long loanId;

    private double loanAmount;
    private double remainingBalance;
    private int term; //months

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(double remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }
}
