package com.eventify.EventService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String location;
    private LocalDateTime datetime;
    private Double price;
}
