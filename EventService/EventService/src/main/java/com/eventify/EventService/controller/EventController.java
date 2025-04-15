package com.eventify.EventService.controller;

import com.eventify.EventService.model.Event;
import com.eventify.EventService.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/user/{email}")
    public List<Event> getEventsByUserEmail(@PathVariable String email) {
        return eventService.getEventsByUserEmail(email);
    }
}
