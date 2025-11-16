package org.qsheker.carrentalservice.patterns;

import org.qsheker.carrentalservice.context.db.models.Car;
import org.qsheker.carrentalservice.context.db.models.CarRental;
import org.qsheker.carrentalservice.context.db.repository.CarRentalRepository;
import org.qsheker.carrentalservice.context.db.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class CarRentalFacade {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarRentalRepository rentalRepository;

    @Autowired
    private CarRentalBuilder carRentalBuilder;

    private static final double QUICK_RENT_PREMIUM = 0.15;

    public CarRental quickRent(Long carId, Long userId, LocalDate pickupDate,
                               LocalDate returnDate, String location) {

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not available"));

        if (!car.isAvailable()) {
            throw new RuntimeException("Car is not available for rental");
        }

        car.setAvailable(false);
        carRepository.save(car);

        long days = ChronoUnit.DAYS.between(pickupDate, returnDate);
        Double basePrice = car.getDailyPrice() * days;

        Double premiumPrice = basePrice * QUICK_RENT_PREMIUM;

        Double totalPrice = basePrice + premiumPrice;

        CarRental rental = carRentalBuilder
                .setCar(carId)
                .setUser(userId)
                .setDates(pickupDate, returnDate)
                .setPickupLocation(location)
                .build();

        rental.setTotalPrice(totalPrice);

        return rentalRepository.save(rental);
    }
}