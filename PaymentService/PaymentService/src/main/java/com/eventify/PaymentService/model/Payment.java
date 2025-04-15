package com.eventify.PaymentService.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;
    private Double amount;
    private String status;
    private LocalDateTime timestamp;

    // Constructor
    public Payment(String userEmail, Double amount) {
        this.userEmail = userEmail;
        this.amount = amount;
        this.timestamp = LocalDateTime.now(); // Set the timestamp to the current time
        this.status = "PENDING"; // Default status when the payment is created
    }

    // Default constructor (needed for JPA) im not sure about this tho
    public Payment() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void markAsCompleted() {
        this.status = "COMPLETED";
    }
}
