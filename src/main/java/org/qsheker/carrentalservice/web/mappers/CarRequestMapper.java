package org.qsheker.carrentalservice.web.mappers;

import org.mapstruct.Mapper;
import org.qsheker.carrentalservice.context.db.models.Car;
import org.qsheker.carrentalservice.web.dto.CarRequestDto;

@Mapper(componentModel = "spring")
public interface CarRequestMapper {
    Car toEntity(CarRequestDto carRequestDto);
}
