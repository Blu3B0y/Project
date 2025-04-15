package com.eventify.EventService.repo;

import com.eventify.EventService.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event, Long> {}