package com.example.carrental.Service;

import com.example.carrental.Entity.Car;
import com.example.carrental.Entity.Rental;
import com.example.carrental.Entity.User;
import com.example.carrental.Repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    public Rental rentCar(Long carId, Long userId, LocalDate rentalDate, LocalDate returnDate) {
        Car car = carService.getCar(carId);
        User user = userService.getUser(userId);
        
        if (car == null || !car.isAvailable()) {
            throw new RuntimeException("Car is not available");
        }
        
        Rental rental = new Rental();
        rental.setCar(car);
        rental.setUser(user);
        rental.setRentalDate(rentalDate);
        rental.setReturnDate(returnDate);
        
        car.setAvailable(false);
        carService.updateCar(car);
        
        return rentalRepository.save(rental);
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }
    
    public void updateRentalStatus(Long rentalId, Rental.RentalStatus status) {
        Rental rental = rentalRepository.findById(rentalId)
            .orElseThrow(() -> new RuntimeException("Rental not found"));
            
        rental.setStatus(status);
        
        if (status == Rental.RentalStatus.APPROVED) {
            rental.getCar().setAvailable(false);
        } else if (status == Rental.RentalStatus.REJECTED) {
            rental.getCar().setAvailable(true);
        }
        
        rentalRepository.save(rental);
    }

    public Object getRentalsByCarId(Long carId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRentalsByCarId'");
    }
}

