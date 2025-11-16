package org.qsheker.hotelservice.web.mapper;

import org.mapstruct.Mapper;
import org.qsheker.hotelservice.context.db.models.Hotel;
import org.qsheker.hotelservice.web.dto.HotelResponse;

@Mapper(componentModel = "spring")
public interface HotelResponseMapper {
    HotelResponse toDto(Hotel hotel);
}
