package com.example.carrental.Controller;




import com.example.carrental.Entity.Car;
import com.example.carrental.Entity.User;
import com.example.carrental.Service.CarService;
import com.example.carrental.Service.RentalService;
import com.example.carrental.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private RentalService rentalService;

    // Home page
    @GetMapping("/")
    public String homePage(@RequestParam(required = false) String searchTerm,
                          @RequestParam(required = false) String year,
                          Model model) {
        List<Car> cars = carService.searchCars(searchTerm);
        model.addAttribute("cars", cars);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("selectedYear", year);
        return "home";
    }

    // Login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // Registration page
    @GetMapping("/user/register")
    public String showRegistrationForm() {
        return "registration";
    }

    // Rent car page
    @GetMapping("/rent/{carId}")
    public String rentCarPage(@PathVariable Long carId, Model model) {
        model.addAttribute("car", carService.getCar(carId));
        model.addAttribute("rentals", rentalService.getRentalsByCarId(carId));
        return "rental";
    }

    // Handle login
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, 
                           @RequestParam String password, 
                           Model model,
                           HttpSession session) {
        User user = userService.loginUser(username, password);
        if (user != null) {
            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());
            
            if ("ROLE_ADMIN".equals(user.getRole())) {
                return "redirect:/admin/rentals";
            } else {
                return "redirect:/";
            }
        }
        model.addAttribute("error", "Invalid credentials");
        return "login";
    }

    // Handle registration
    @PostMapping("/register")
    public String registerUser(@RequestParam String username, 
                         @RequestParam String email,
                         @RequestParam String password,
                         Model model) {
        try {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password); // In production, make sure to hash the password
            userService.registerUser(user);
            model.addAttribute("success", "Registration successful! Please login.");
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "registration";
        }
    }

    @GetMapping("/search")
    public String searchCars(@RequestParam(required = false) String searchTerm,
                            @RequestParam(required = false) String sortBy,
                            Model model) {
        List<Car> cars = carService.searchCars(searchTerm);
        model.addAttribute("cars", cars);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("selectedSort", sortBy);
        return "home";
    }
}
