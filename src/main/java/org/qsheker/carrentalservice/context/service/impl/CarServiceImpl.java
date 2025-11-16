package org.qsheker.carrentalservice.context.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.qsheker.carrentalservice.context.db.models.Car;
import org.qsheker.carrentalservice.context.db.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findAllAvailable() {
        return carRepository.findAllByAvailable(true);
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Car not found with id: "+id)
        );
    }
}
