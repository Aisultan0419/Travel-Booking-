package org.qsheker.carrentalservice.context.db.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String model;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Double dailyPrice;

    private String location;

    private boolean available;

    @PrePersist
    public void prePersist(){
        this.available=true;
    }
}