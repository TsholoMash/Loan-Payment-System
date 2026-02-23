package com.example.loanpayment.payment;

import com.example.loanpayment.exception.OverPaymentException;
import com.example.loanpayment.loan.Loan;
import com.example.loanpayment.loan.LoanRepository;
import com.example.loanpayment.loan.LoanStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private PaymentService paymentService;

    private Loan loan;

    @BeforeEach
    void setup() {
        loan = new Loan();
        loan.setLoanId(1L);
        loan.setLoanAmount(new BigDecimal("10000.00"));
        loan.setRemainingBalance(new BigDecimal("10000.00"));
        loan.setStatus(LoanStatus.ACTIVE);
    }

    @Test
    void shouldReduceLoanBalanceWhenPaymentIsMade() {

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(paymentRepository.save(any(Payment.class))).thenAnswer(i -> i.getArgument(0));

        paymentService.makePayment(1L, new BigDecimal("2000.00"));

        assertEquals(new BigDecimal("8000.00"), loan.getRemainingBalance());

        verify(loanRepository).save(loan);
        verify(paymentRepository).save(any(Payment.class));
    }

    @Test
    void shouldThrowExceptionWhenOverpaymentOccurs() {

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

        assertThrows(OverPaymentException.class, () -> paymentService.makePayment(1L, new BigDecimal("15000.00")));

        verify(paymentRepository, never()).save(any());
    }

    @Test
    void shouldSetLoanToSettledWhenFullyPaid() {

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(paymentRepository.save(any(Payment.class))).thenAnswer(i -> i.getArgument(0));

        paymentService.makePayment(1L, new BigDecimal("10000.00"));

        assertEquals(0, loan.getRemainingBalance().compareTo(BigDecimal.ZERO));
        assertEquals(LoanStatus.SETTLED, loan.getStatus());

        verify(loanRepository).save(loan);
    }

}
