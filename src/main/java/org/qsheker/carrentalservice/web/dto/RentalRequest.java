package org.qsheker.carrentalservice.web.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RentalRequest {
    private Long carId;
    private Long userId;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private String Location;
}