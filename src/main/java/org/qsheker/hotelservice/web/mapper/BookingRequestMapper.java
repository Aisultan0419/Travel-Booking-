package org.qsheker.hotelservice.web.mapper;

import org.mapstruct.Mapper;
import org.qsheker.hotelservice.context.db.models.HotelBooking;
import org.qsheker.hotelservice.web.dto.BookingRequest;

@Mapper(componentModel = "spring")
public interface BookingRequestMapper {
    HotelBooking toEntity(BookingRequest bookingRequest);
}
