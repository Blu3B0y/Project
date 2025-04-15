package com.eventify.UserService.service;

import com.eventify.UserService.model.User;
import com.eventify.UserService.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public void save(User user) {
        repo.save(user);
    }

    public boolean authenticate(User user) {
        User existing = repo.findByEmail(user.getEmail());
        return existing != null && existing.getPassword().equals(user.getPassword());
    }

    public User findByEmail(String email) {
        return repo.findByEmail(email);
    }
}
