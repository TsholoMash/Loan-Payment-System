package com.example.loanpayment.payment;

import com.example.loanpayment.exception.OverPaymentException;
import com.example.loanpayment.exception.ResourceNotFoundException;
import com.example.loanpayment.loan.Loan;
import com.example.loanpayment.loan.LoanRepository;
import com.example.loanpayment.loan.LoanStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private LoanRepository loanRepository;

    public Payment makePayment(Long loanId, double amount) throws ResourceNotFoundException, OverPaymentException {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));

        if (loan.getStatus() == LoanStatus.SETTLED) {
            throw new OverPaymentException("Loan already settled");
        }

        if(amount > loan.getRemainingBalance()) {
            throw new OverPaymentException("Payment exceed remaining balance");
        }

        loan.setRemainingBalance(loan.getRemainingBalance() - amount);
        if (loan.getRemainingBalance() == 0) {
            loan.setStatus(LoanStatus.SETTLED);
        }
        loanRepository.save(loan);

        Payment payment = new Payment();
        payment.setLoanId(loanId);
        payment.setPaymentAmount(amount);
        return paymentRepository.save(payment);
    }
}
