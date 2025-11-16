package org.qsheker.carrentalservice.web.mappers;

import org.mapstruct.Mapper;
import org.qsheker.carrentalservice.context.db.models.Car;
import org.qsheker.carrentalservice.web.dto.CarResponseDto;

@Mapper(componentModel = "spring")
public interface CarResponseDtoMapper {
    CarResponseDto toDto(Car car);

    Car toEntity(CarResponseDto car);
}
