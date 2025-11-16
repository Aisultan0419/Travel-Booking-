package org.qsheker.carrentalservice.context.service.impl;


import jakarta.transaction.Transactional;
import org.qsheker.carrentalservice.context.db.models.Car;
import org.qsheker.carrentalservice.context.db.models.CarRental;
import org.qsheker.carrentalservice.context.db.models.RentalStatus;
import org.qsheker.carrentalservice.context.db.repository.CarRentalRepository;
import org.qsheker.carrentalservice.context.db.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
public class CarRentalService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarRentalRepository rentalRepository;

    public CarRental rentCar(Long carId, Long userId, LocalDate pickupDate, LocalDate returnDate, String pickupLocation) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        if (!car.isAvailable()) {
            throw new RuntimeException("Car is not available for rental");
        }

        car.setAvailable(false);
        carRepository.save(car);

        long days = ChronoUnit.DAYS.between(pickupDate, returnDate);
        Double totalPrice = car.getDailyPrice() * days;

        CarRental rental = new CarRental();
        rental.setCarId(carId);
        rental.setUserId(userId);
        rental.setPickupDate(pickupDate);
        rental.setReturnDate(returnDate);
        rental.setPickupLocation(pickupLocation);
        rental.setTotalPrice(totalPrice);
        rental.setStatus(RentalStatus.CONFIRMED);

        return rentalRepository.save(rental);
    }

    public List<CarRental> getUserRentals(Long userId) {
        return rentalRepository.findByUserId(userId);
    }

    public CarRental cancelRental(Long rentalId) {
        CarRental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        Car car = carRepository.findById(rental.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));
        car.setAvailable(true);
        carRepository.save(car);

        rental.setStatus(RentalStatus.CANCELLED);
        return rentalRepository.save(rental);
    }

    public CarRental getRentalDetails(Long id){
        return rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found"));
    }
}