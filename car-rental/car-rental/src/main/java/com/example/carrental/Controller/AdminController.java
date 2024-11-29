package com.example.carrental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.carrental.Entity.Rental;
import com.example.carrental.Entity.Rental.RentalStatus;

import com.example.carrental.Service.RentalService;


import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private RentalService rentalService;
    
    @GetMapping("/rentals")
    public String viewRentals(Model model, HttpSession session) {
        System.out.println("Session role: " + session.getAttribute("role"));
        
        if (!"ROLE_ADMIN".equals(session.getAttribute("role"))) {
            return "redirect:/login";
        }
        
        List<Rental> rentals = rentalService.getAllRentals();
        model.addAttribute("rentals", rentals);
        return "admin/rentals";
    }
    
    @PostMapping("/rentals")
    public String handleRentalsPost(Model model, HttpSession session) {
        return "redirect:/admin/rentals";
    }
    
    @PostMapping("/rentals/{rentalId}/approve")
    public String approveRental(@PathVariable Long rentalId) {
        rentalService.updateRentalStatus(rentalId, RentalStatus.APPROVED);
        return "redirect:/admin/rentals";
    }
    
    @PostMapping("/rentals/{rentalId}/reject")
    public String rejectRental(@PathVariable Long rentalId) {
        rentalService.updateRentalStatus(rentalId, RentalStatus.REJECTED);
        return "redirect:/admin/rentals";
    }
} 