package org.qsheker.hotelservice.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelSearchCriteria {
    private String city;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer guests;
    private String location;
    private Double maxPrice;
    private Double minRating;
}
