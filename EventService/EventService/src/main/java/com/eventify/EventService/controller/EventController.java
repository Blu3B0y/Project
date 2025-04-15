package com.eventify.EventService.controller;

import com.eventify.EventService.model.Event;
import com.eventify.EventService.repo.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventRepo repo;

    @PostMapping
    public Event create(@RequestBody Event e) {
        return repo.save(e);
    }

    @GetMapping
    public List<Event> all() {
        return repo.findAll();
    }
}
