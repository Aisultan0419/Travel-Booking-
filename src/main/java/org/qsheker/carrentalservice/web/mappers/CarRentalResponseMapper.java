package org.qsheker.carrentalservice.web.mappers;

import org.mapstruct.Mapper;
import org.qsheker.carrentalservice.context.db.models.CarRental;
import org.qsheker.carrentalservice.web.dto.CarRentalResponse;

@Mapper(componentModel = "spring")
public interface CarRentalResponseMapper {
    CarRentalResponse toDto(CarRental car);
}
