package org.qsheker.hotelservice.context.service;

import org.qsheker.hotelservice.context.db.models.Hotel;
import org.qsheker.hotelservice.context.pattern.strategy.StrategyType;
import org.qsheker.hotelservice.web.dto.*;

import java.util.List;

public interface HotelService {
    List<HotelResponse> getAllHotels();

    HotelResponse getHotelById(Long id);

    HotelResponse createHotel(CreateHotelRequest request);

    List<HotelResponse> searchHotelsWithStrategy(HotelSearchRequest request, StrategyType strategyType);

    AvailabilityResponse checkAvailability(Long hotelId, AvailabilityRequest request);

    BookingResponse bookHotel(BookingRequest request);

    BookingResponse getBookingById(Long bookingId);

    List<BookingResponse> getUserBookings(Long userId);

    BookingResponse cancelBooking(Long bookingId);

}
