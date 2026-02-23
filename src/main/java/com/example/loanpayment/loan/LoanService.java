package com.example.loanpayment.loan;

import com.example.loanpayment.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public Loan createLoan(double amount, int term) {
        Loan loan = new Loan();
        loan.setLoanAmount(amount);
        loan.setRemainingBalance(amount);   //initial amount is the one remaining
        loan.setTerm(term);
        loan.setStatus(LoanStatus.ACTIVE);
        return loanRepository.save(loan);
    }

    public Loan getLoan(Long loanId) throws ResourceNotFoundException {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not fount"));
    }

    public void settleLoan(Loan loan) {
        loan.setRemainingBalance(0);
        loan.setStatus(LoanStatus.SETTLED);
        loanRepository.save(loan);
    }
}
