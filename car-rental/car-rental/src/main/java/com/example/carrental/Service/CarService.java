package com.example.carrental.Service;



import com.example.carrental.Entity.Car;
import com.example.carrental.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCar(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public void updateCarAvailability(Long carId, boolean available) {
        Car car = carRepository.findById(carId).orElse(null);
        if (car != null) {
            car.setAvailable(available);
            carRepository.save(car);
        }
    }

    public Car updateCar(Car car) {
        return carRepository.save(car);
    }

    public List<Car> searchCars(String searchTerm) {
        if (searchTerm != null && !searchTerm.isEmpty()) {
            return carRepository.findByMakeContainingOrModelContainingIgnoreCase(searchTerm, searchTerm);
        }
        return getAllCars();
    }
}

