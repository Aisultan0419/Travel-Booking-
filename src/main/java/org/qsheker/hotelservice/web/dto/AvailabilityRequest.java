package org.qsheker.hotelservice.web.dto;

import lombok.Data;

@Data
public class AvailabilityRequest {
    private String checkIn;
    private String checkOut;
    private Integer guests;
}
