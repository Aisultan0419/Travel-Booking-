package org.qsheker.hotelservice.web.dto;

import lombok.Data;

@Data
public class BookingResponse {
    private Long id;
    private Long userId;
    private HotelResponse hotel;
    private String checkIn;
    private String checkOut;
    private Integer guests;
    private Double totalPrice;
    private String status;
}
