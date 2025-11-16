package org.qsheker.carrentalservice.context.db.repository;

import org.qsheker.carrentalservice.context.db.models.CarRental;
import org.qsheker.carrentalservice.context.db.models.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRentalRepository extends JpaRepository<CarRental, Long> {
    List<CarRental> findByUserId(Long userId);
    List<CarRental> findByStatus(RentalStatus status);
}
