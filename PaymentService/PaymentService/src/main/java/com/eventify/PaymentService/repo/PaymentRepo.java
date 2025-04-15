package com.eventify.PaymentService.repo;

import com.eventify.PaymentService.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
    List<Payment> findByUserEmail(String email);
}