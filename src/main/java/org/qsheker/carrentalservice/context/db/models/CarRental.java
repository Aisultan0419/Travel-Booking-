package org.qsheker.carrentalservice.context.db.models;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "car_rentals")
public class CarRental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(name = "car_id")
    private Long carId;

    @Column(nullable = false)
    private LocalDate pickupDate;

    @Column(nullable = false)
    private LocalDate returnDate;

    private String pickupLocation;

    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private RentalStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
