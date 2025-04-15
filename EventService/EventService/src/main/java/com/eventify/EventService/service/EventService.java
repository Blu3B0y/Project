package com.eventify.EventService.service;

import com.eventify.EventService.model.Event;
import com.eventify.EventService.repo.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepo eventRepo;

    // Create a new event
    public Event createEvent(Event event) {
        return eventRepo.save(event);
    }

    // Get all events
    public List<Event> getAllEvents() {
        return eventRepo.findAll();
    }

    // Get events by user email
    public List<Event> getEventsByUserEmail(String email) {
        return eventRepo.findByUserEmail(email);
    }
}
