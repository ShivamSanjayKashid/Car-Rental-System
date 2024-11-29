package com.example.carrental.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.carrental.Entity.User;
import com.example.carrental.Service.UserService;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) {
        // Create admin if it doesn't exist
        if (!userService.existsByEmail("admin@example.com")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword("admin123");
            admin.setRole("ROLE_ADMIN");
            userService.saveUser(admin);
            System.out.println("Admin user created with:");
            System.out.println("Username: admin");
            System.out.println("Password: admin123");
        }
    }
} 