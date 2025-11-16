package org.qsheker.carrentalservice.patterns;

import org.qsheker.carrentalservice.context.db.models.Car;
import org.qsheker.carrentalservice.context.db.models.CarRental;
import org.qsheker.carrentalservice.context.db.models.RentalStatus;
import org.qsheker.carrentalservice.context.db.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
public class CarRentalBuilder {
    @Autowired
    private CarRepository carRepository;

    private Long carId;
    private Long userId;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private String pickupLocation;

    public CarRentalBuilder setCar(Long carId) {
        this.carId = carId;
        return this;
    }

    public CarRentalBuilder setUser(Long userId) {
        this.userId = userId;
        return this;
    }

    public CarRentalBuilder setDates(LocalDate pickup, LocalDate returnDate) {
        this.pickupDate = pickup;
        this.returnDate = returnDate;
        return this;
    }

    public CarRentalBuilder setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
        return this;
    }

    public CarRental build() {
        if (carId == null || userId == null || pickupDate == null || returnDate == null) {
            throw new IllegalStateException("Missing required rental information");
        }

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        long rentalDays = ChronoUnit.DAYS.between(pickupDate, returnDate);
        Double totalPrice = car.getDailyPrice() * rentalDays;

        CarRental rental = new CarRental();
        rental.setCarId(this.carId);
        rental.setUserId(this.userId);
        rental.setPickupDate(this.pickupDate);
        rental.setReturnDate(this.returnDate);
        rental.setPickupLocation(this.pickupLocation);
        rental.setTotalPrice(totalPrice);
        rental.setStatus(RentalStatus.CONFIRMED);

        return rental;
    }
}