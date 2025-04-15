package com.eventify.PaymentService.service;

import com.eventify.PaymentService.model.Payment;
import com.eventify.PaymentService.repo.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo repo;

    // Create a new payment and set the timestamp and status
    public Payment createPayment(Payment payment) {
        // Set the timestamp to the current time
        payment.setTimestamp(LocalDateTime.now());
        // Set the status to "PENDING" initially
        payment.setStatus("PENDING");
        // Save the payment to the repository
        return repo.save(payment);
    }

    // Method to update the payment status to "COMPLETED" once processed
    public Payment completePayment(Long paymentId) {
        // Retrieve the payment by ID
        Payment payment = repo.findById(paymentId).orElseThrow(() -> new RuntimeException("Payment not found"));

        // Update the status to "COMPLETED"
        payment.setStatus("COMPLETED");

        // Update the timestamp to current time
        payment.setTimestamp(LocalDateTime.now());

        // Save the updated payment status to the repository
        return repo.save(payment);
    }

    // Fetch payments for a specific user based on their email
    public List<Payment> getPaymentsByEmail(String email) {
        return repo.findByUserEmail(email);
    }

    // Fetch all payments
    public List<Payment> getAllPayments() {
        return repo.findAll();
    }
}
