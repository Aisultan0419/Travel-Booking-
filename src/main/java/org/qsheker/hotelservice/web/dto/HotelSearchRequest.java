package org.qsheker.hotelservice.web.dto;

import lombok.Data;

@Data
public class HotelSearchRequest {
    private String city;
    private String checkIn;
    private String checkOut;
    private Integer guests;
    private Double maxPrice;
    private Double minRating;
}
