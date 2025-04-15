package com.eventify.UserService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Eventify is running!";
    }

    @GetMapping("/user/home")
    public String userHome() {
        return "Welcome User!";
    }

    @GetMapping("/admin/home")
    public String adminHome() {
        return "Welcome Admin!";
    }
}
