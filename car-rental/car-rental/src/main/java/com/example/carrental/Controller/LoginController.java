package com.example.carrental.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.carrental.Entity.User;
import com.example.carrental.Service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    
    @PostMapping("/auth/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User user = userService.authenticate(username, password);
        if (user != null) {
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());
            
            if ("ROLE_ADMIN".equals(user.getRole())) {
                return "redirect:/admin/rentals";
            }
            return "redirect:/home";
        }
        return "redirect:/login?error";
    }
} 