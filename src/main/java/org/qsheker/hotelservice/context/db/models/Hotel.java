package org.qsheker.hotelservice.context.db.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    private Double pricePerNight;

    private Double rating;
}