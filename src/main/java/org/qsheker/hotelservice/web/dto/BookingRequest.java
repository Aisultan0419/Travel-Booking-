package org.qsheker.hotelservice.web.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequest {
    private Long hotelId;
    private Long userId;
    private String checkIn;
    private String checkOut;
    private Integer guests;
}
