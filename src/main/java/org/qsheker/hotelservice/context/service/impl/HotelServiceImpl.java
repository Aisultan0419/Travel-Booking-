package org.qsheker.hotelservice.context.service.impl;

import jakarta.transaction.Transactional;
import org.qsheker.hotelservice.context.db.models.BookingStatus;
import org.qsheker.hotelservice.context.db.models.Hotel;
import org.qsheker.hotelservice.context.db.models.HotelBooking;
import org.qsheker.hotelservice.context.db.repository.HotelBookingRepository;
import org.qsheker.hotelservice.context.db.repository.HotelRepository;
import org.qsheker.hotelservice.context.pattern.factory.StrategyFactory;
import org.qsheker.hotelservice.context.pattern.strategy.SearchStrategy;
import org.qsheker.hotelservice.context.pattern.strategy.StrategyType;
import org.qsheker.hotelservice.context.service.HotelService;
import org.qsheker.hotelservice.web.dto.*;
import org.qsheker.hotelservice.web.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelBookingRepository bookingRepository;

    @Autowired
    private StrategyFactory strategyFactory;

    @Autowired
    private HotelResponseMapper hotelResponseMapper;

    @Autowired
    private CreateHotelRequestMapper createHotelRequestMapper;

    @Autowired
    private HotelSearchRequestMapper hotelSearchRequestMapper;

    @Autowired
    private BookingRequestMapper bookingRequestMapper;

    @Autowired
    private BookingResponseMapper bookingResponseMapper;
    @Autowired
    private SearchToCriteriaMapper searchToCriteriaMapper;

    @Override
    public List<HotelResponse> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(hotelResponseMapper::toDto)
                .toList();
    }

    @Override
    public HotelResponse getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));
        return hotelResponseMapper.toDto(hotel);
    }

    @Override
    public HotelResponse createHotel(CreateHotelRequest request) {
        var hotel = createHotelRequestMapper.toEntity(request);
        hotelRepository.save(hotel);
        return hotelResponseMapper.toDto(hotel);
    }

    @Override
    public List<HotelResponse> searchHotelsWithStrategy(HotelSearchRequest request, StrategyType strategyType) {
        List<Hotel> allHotels = hotelRepository.findByLocationContainingIgnoreCase(request.getCity());

        HotelSearchCriteria criteria = searchToCriteriaMapper.toCriteria(request);

        SearchStrategy searchStrategy = strategyFactory.of(strategyType);
        List<Hotel> filteredHotels = searchStrategy.search(allHotels, criteria);

        return filteredHotels.stream()
                .map(hotelResponseMapper::toDto)
                .toList();
    }

    @Override
    public AvailabilityResponse checkAvailability(Long hotelId, AvailabilityRequest request) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        LocalDate checkInDate = LocalDate.parse(request.getCheckIn());
        LocalDate checkOutDate = LocalDate.parse(request.getCheckOut());

        boolean isAvailable = isHotelAvailable(hotel, checkInDate, checkOutDate);

        long days = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        Double totalPrice = hotel.getPricePerNight() * days;

        return new AvailabilityResponse(isAvailable, totalPrice,
                isAvailable ? "Hotel is available" : "Hotel is not available for selected dates");
    }

    @Override
    public BookingResponse bookHotel(BookingRequest request) {
        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        LocalDate checkInDate = LocalDate.parse(request.getCheckIn());
        LocalDate checkOutDate = LocalDate.parse(request.getCheckOut());

        if (!isHotelAvailable(hotel, checkInDate, checkOutDate)) {
            throw new RuntimeException("Hotel is not available for the selected dates");
        }

        long days = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        Double totalPrice = hotel.getPricePerNight() * days;

        HotelBooking booking = bookingRequestMapper.toEntity(request);
        booking.setHotel(hotel);
        booking.setTotalPrice(totalPrice);
        booking.setStatus(BookingStatus.CONFIRMED);

        HotelBooking savedBooking = bookingRepository.save(booking);
        return bookingResponseMapper.toDto(savedBooking);
    }

    @Override
    public BookingResponse getBookingById(Long bookingId) {
        HotelBooking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
        return bookingResponseMapper.toDto(booking);
    }

    @Override
    public List<BookingResponse> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId).stream()
                .map(bookingResponseMapper::toDto)
                .toList();
    }

    @Override
    public BookingResponse cancelBooking(Long bookingId) {
        HotelBooking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(BookingStatus.CANCELLED);
        HotelBooking updatedBooking = bookingRepository.save(booking);
        return bookingResponseMapper.toDto(updatedBooking);
    }

    private boolean isHotelAvailable(Hotel hotel, LocalDate checkIn, LocalDate checkOut) {
        List<HotelBooking> overlappingBookings = bookingRepository
                .findOverlappingBookings(hotel.getId(), checkIn, checkOut, BookingStatus.CONFIRMED);
        return overlappingBookings.isEmpty();
    }
}