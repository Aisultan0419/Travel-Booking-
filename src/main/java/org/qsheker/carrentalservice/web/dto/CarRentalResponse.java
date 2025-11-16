package org.qsheker.carrentalservice.web.dto;

import lombok.Data;


@Data
public class CarRentalResponse {
    private Long id;
    private String status;
    private Double totalPrice;
    private String confirmationNumber;
}
