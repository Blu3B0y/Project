package com.eventify.EventService.repo;

import com.eventify.EventService.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepo extends JpaRepository<Event, Long> {
    List<Event> findByUserEmail(String email);
}