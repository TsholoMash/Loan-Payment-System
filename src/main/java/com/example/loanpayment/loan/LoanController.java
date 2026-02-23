package com.example.loanpayment.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @PostMapping
    public Loan createLoan(@RequestBody CreateLoanRequest request) {
        return loanService.createLoan(request.getLoanAmount(), request.getTerm());
    }

    @GetMapping("/{id}")
    public Loan getLoan(@PathVariable Long id) {
        return loanService.getLoan(id);
    }
}
