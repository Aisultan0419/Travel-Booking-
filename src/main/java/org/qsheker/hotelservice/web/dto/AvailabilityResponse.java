package org.qsheker.hotelservice.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AvailabilityResponse {
    private boolean available;
    private Double totalPrice;
    private String message;
}
