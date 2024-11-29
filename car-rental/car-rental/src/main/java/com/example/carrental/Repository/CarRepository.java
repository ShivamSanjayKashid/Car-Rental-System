package com.example.carrental.Repository;



import com.example.carrental.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByMakeContainingOrModelContainingIgnoreCase(String make, String model);
    List<Car> findByYear(String year);
}

