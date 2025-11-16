package org.qsheker.hotelservice.web.dto;

import lombok.Data;

@Data
public class HotelResponse {
    private Long id;
    private String name;
    private String location;
    private Double pricePerNight;
    private Double rating;
}
