package com.example.loanpayment.payment;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Payment makePayment(@Valid @RequestBody CreatePaymentRequest request) {
        return paymentService.makePayment(request.getLoanId(), request.getPaymentAmount());
    }
}
