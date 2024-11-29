package com.example.carrental.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.example.carrental.Entity.Rental;

import jakarta.servlet.http.HttpSession;

@Controller
public class RentalController {
    // ... other code ...

    @PostMapping("/rent/{carId}")
    public String rentCar(@PathVariable Long carId, @ModelAttribute Rental rental, 
                         HttpSession session,
                         RedirectAttributes redirectAttributes) {
        // Get user details from session
        String username = (String) session.getAttribute("username");
        rental.setUsername(username);  // Assuming Rental has a username field
        
        // Your existing rental logic here
        
        // Add success message
        redirectAttributes.addFlashAttribute("successMessage", 
            "Car booked successfully! Your rental has been confirmed.");
        
        return "redirect:/home";  // Redirect to home page
    }
} 