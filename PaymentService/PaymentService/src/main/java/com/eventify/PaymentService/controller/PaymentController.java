package com.eventify.PaymentService.controller;

import com.eventify.PaymentService.model.Payment;
import com.eventify.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    // Endpoint to create a new payment
    @PostMapping("/make")
    public Payment makePayment(@RequestBody Payment payment) {
        return service.createPayment(payment);
    }

    // Endpoint to get payments by user email
    @GetMapping("/user/{email}")
    public List<Payment> getUserPayments(@PathVariable String email) {
        return service.getPaymentsByEmail(email);
    }

    // Endpoint to get all payments
    @GetMapping("/all")
    public List<Payment> getAllPayments() {
        return service.getAllPayments();
    }

    // Endpoint to complete payment by payment ID
    @PostMapping("/complete/{paymentId}")
    public Payment completePayment(@PathVariable Long paymentId) {
        return service.completePayment(paymentId);
    }
}
