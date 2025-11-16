package org.qsheker.hotelservice.web.mapper;

import org.mapstruct.Mapper;
import org.qsheker.hotelservice.context.db.models.HotelBooking;
import org.qsheker.hotelservice.web.dto.BookingResponse;

@Mapper(componentModel = "spring")
public interface BookingResponseMapper {
    BookingResponse toDto(HotelBooking hotel);
}
