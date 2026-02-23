package com.example.loanpayment.loan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    private Loan loan;
    private final BigDecimal LOAN_AMOUNT = new BigDecimal("10000.00");

    @BeforeEach
    void setup() {
        loan = new Loan();
        loan.setLoanId(1L);
        loan.setLoanAmount(LOAN_AMOUNT);
        loan.setRemainingBalance(LOAN_AMOUNT);
        loan.setTerm(12);
        loan.setStatus(LoanStatus.ACTIVE);
    }

    @Test
    void shouldCreateLoanSuccessfully() {
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        Loan created = loanService.createLoan(LOAN_AMOUNT, 12);

        assertNotNull(created);
        assertEquals(LOAN_AMOUNT, created.getLoanAmount());
        assertEquals(LoanStatus.ACTIVE, created.getStatus());

        verify(loanRepository, times(1)).save(any(Loan.class));
    }
}
