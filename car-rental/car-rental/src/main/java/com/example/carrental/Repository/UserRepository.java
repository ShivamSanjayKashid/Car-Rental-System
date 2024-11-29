package com.example.carrental.Repository;



import com.example.carrental.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByEmail(String email);
}

