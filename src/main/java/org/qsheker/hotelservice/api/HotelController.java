package org.qsheker.hotelservice.api;

import org.qsheker.hotelservice.context.pattern.strategy.StrategyType;
import org.qsheker.hotelservice.context.service.HotelService;
import org.qsheker.hotelservice.web.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public ResponseEntity<List<HotelResponse>> getAllHotels() {
        List<HotelResponse> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponse> getHotelById(@PathVariable Long id) {
        HotelResponse hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotel);
    }

    @PostMapping
    public ResponseEntity<HotelResponse> createHotel(@RequestBody CreateHotelRequest request) {
        HotelResponse hotel = hotelService.createHotel(request);
        return ResponseEntity.ok(hotel);
    }

    @PostMapping("/search")
    public ResponseEntity<List<HotelResponse>> searchHotels(@RequestBody HotelSearchRequest request,
                                                            @RequestParam(value = "strategyMode", defaultValue = "PRICE")StrategyType strategyType) {
        List<HotelResponse> hotels = hotelService.searchHotelsWithStrategy(request, strategyType);
        return ResponseEntity.ok(hotels);
    }

    @PostMapping("/{id}/availability")
    public ResponseEntity<AvailabilityResponse> checkAvailability(
            @PathVariable Long id,
            @RequestBody AvailabilityRequest request) {
        AvailabilityResponse response = hotelService.checkAvailability(id, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/book")
    public ResponseEntity<BookingResponse> bookHotel(@RequestBody BookingRequest request) {
        BookingResponse booking = hotelService.bookHotel(request);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable Long id) {
        BookingResponse booking = hotelService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/users/{userId}/bookings")
    public ResponseEntity<List<BookingResponse>> getUserBookings(@PathVariable Long userId) {
        List<BookingResponse> bookings = hotelService.getUserBookings(userId);
        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/bookings/{id}/cancel")
    public ResponseEntity<BookingResponse> cancelBooking(@PathVariable Long id) {
        BookingResponse booking = hotelService.cancelBooking(id);
        return ResponseEntity.ok(booking);
    }
}