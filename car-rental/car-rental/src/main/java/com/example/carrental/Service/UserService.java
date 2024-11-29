package com.example.carrental.Service;

import com.example.carrental.Entity.User;
import com.example.carrental.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        System.out.println("Login attempt - Username: " + username);
        System.out.println("User found: " + (user != null));
        if (user != null) {
            System.out.println("Password match: " + user.getPassword().equals(password));
            System.out.println("User role: " + user.getRole());
        }
        
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}

