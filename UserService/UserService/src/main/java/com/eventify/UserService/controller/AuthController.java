package com.eventify.UserService.controller;

import com.eventify.UserService.model.User;
import com.eventify.UserService.service.UserService;
import com.eventify.UserService.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private UserService service;
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (service.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        // default to USER if role not set
        if (user.getRole() == null) user.setRole("USER");

        service.save(user);
        return ResponseEntity.ok("Registered!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        if (service.authenticate(user)) {
            User dbUser = service.findByEmail(user.getEmail());
            String token = jwtUtil.generateToken(dbUser);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @GetMapping("/user")
    public ResponseEntity<String> userAccess() {
        return ResponseEntity.ok("Hello USER, you are authorized!");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> adminAccess() {
        return ResponseEntity.ok("Hello ADMIN, you are authorized!");
    }

}
