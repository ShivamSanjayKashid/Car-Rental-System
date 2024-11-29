package com.example.carrental.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.carrental.Entity.User;

import org.springframework.ui.Model;

@Controller
public class AuthController {
    
    @GetMapping("/auth/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
} 