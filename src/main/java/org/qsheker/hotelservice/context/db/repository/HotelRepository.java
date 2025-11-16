package org.qsheker.hotelservice.context.db.repository;

import org.qsheker.hotelservice.context.db.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByLocationContainingIgnoreCase(String location);
    List<Hotel> findByPricePerNightBetween(Double minPrice, Double maxPrice);
}
