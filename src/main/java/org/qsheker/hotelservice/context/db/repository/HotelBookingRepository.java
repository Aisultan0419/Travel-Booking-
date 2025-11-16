package org.qsheker.hotelservice.context.db.repository;

import org.qsheker.hotelservice.context.db.models.BookingStatus;
import org.qsheker.hotelservice.context.db.models.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HotelBookingRepository extends JpaRepository<HotelBooking, Long> {
    List<HotelBooking> findByUserId(Long userId);

    @Query("SELECT b FROM HotelBooking b WHERE " +
            "b.hotel.id = :hotelId AND " +
            "b.status = :status AND " +
            "b.checkIn < :checkOut AND " +
            "b.checkOut > :checkIn")
    List<HotelBooking> findOverlappingBookings(
            @Param("hotelId") Long hotelId,
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut,
            @Param("status") BookingStatus status);
}