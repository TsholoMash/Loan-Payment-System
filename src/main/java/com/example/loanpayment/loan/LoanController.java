package com.example.loanpayment.loan;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @PostMapping
    public Loan createLoan(@Valid @RequestBody CreateLoanRequest request) {
        return loanService.createLoan(request.getLoanAmount(), request.getTerm());
    }

    @GetMapping("/{loanId}")
    public Loan getLoan(@PathVariable Long loanId) {
        return loanService.getLoan(loanId);
    }
}
