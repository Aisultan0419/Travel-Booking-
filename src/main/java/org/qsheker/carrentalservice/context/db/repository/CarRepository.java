package org.qsheker.carrentalservice.context.db.repository;

import org.qsheker.carrentalservice.context.db.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByLocationContainingIgnoreCase(String location);
    List<Car> findByCategory(String category);
    List<Car> findByDailyPriceBetween(Double minPrice, Double maxPrice);
    List<Car> findAllByAvailable(boolean available);
}
