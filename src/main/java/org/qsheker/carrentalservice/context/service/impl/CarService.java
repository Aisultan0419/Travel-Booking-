package org.qsheker.carrentalservice.context.service.impl;

import org.qsheker.carrentalservice.context.db.models.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();
    Car findById(Long id);
    Car save(Car car);
    List<Car> findAllAvailable();
}
