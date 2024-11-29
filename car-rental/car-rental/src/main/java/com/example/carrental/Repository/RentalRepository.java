package com.example.carrental.Repository;


import com.example.carrental.Entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    // Custom query methods if needed
}

