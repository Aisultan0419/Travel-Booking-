package org.qsheker.carrentalservice.web.dto;
import lombok.Data;
import org.qsheker.carrentalservice.context.db.models.Category;

@Data
public class CarResponseDto {
    private String model;
    private Category category;
    private Double dailyPrice;
    private String location;
}
