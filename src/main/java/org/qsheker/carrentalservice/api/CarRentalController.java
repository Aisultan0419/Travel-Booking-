package org.qsheker.carrentalservice.api;

import org.qsheker.carrentalservice.context.db.models.CarRental;
import org.qsheker.carrentalservice.patterns.CarRentalFacade;
import org.qsheker.carrentalservice.context.service.impl.CarRentalService;
import org.qsheker.carrentalservice.web.dto.RentalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car-rental")
public class CarRentalController {

    @Autowired
    private CarRentalService carRentalService;

    @Autowired
    private CarRentalFacade carRentalFacade;

    @GetMapping("/{id}")
    public ResponseEntity<CarRental> getRentalDetails(@PathVariable Long id){
        CarRental response = carRentalService.getRentalDetails(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/quick-rent")
    public ResponseEntity<CarRental> quickRent(@RequestBody RentalRequest request) {
        CarRental rental = carRentalFacade.quickRent(
                request.getCarId(),
                request.getUserId(),
                request.getPickupDate(),
                request.getReturnDate(),
                request.getLocation()
        );
        return ResponseEntity.ok(rental);
    }

    @PostMapping("/rent")
    public ResponseEntity<CarRental> rentCar(@RequestBody RentalRequest request) {
        CarRental rental = carRentalService.rentCar(
                request.getCarId(),
                request.getUserId(),
                request.getPickupDate(),
                request.getReturnDate(),
                request.getLocation()
        );
        return ResponseEntity.ok(rental);
    }

    @GetMapping("/users/{userId}/rentals")
    public ResponseEntity<List<CarRental>> getUserRentals(@PathVariable Long userId) {
        List<CarRental> rentals = carRentalService.getUserRentals(userId);
        return ResponseEntity.ok(rentals);
    }

    @PutMapping("/rentals/{rentalId}/cancel")
    public ResponseEntity<CarRental> cancelRental(@PathVariable Long rentalId) {
        CarRental rental = carRentalService.cancelRental(rentalId);
        return ResponseEntity.ok(rental);
    }
}