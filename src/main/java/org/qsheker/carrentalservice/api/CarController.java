package org.qsheker.carrentalservice.api;

import org.qsheker.carrentalservice.context.db.models.Category;
import org.qsheker.carrentalservice.context.service.impl.CarService;
import org.qsheker.carrentalservice.web.dto.CarRequestDto;
import org.qsheker.carrentalservice.web.dto.CarResponseDto;
import org.qsheker.carrentalservice.web.mappers.CarRequestMapper;
import org.qsheker.carrentalservice.web.mappers.CarResponseDtoMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cars")
public class CarController {

    private final CarService carService;
    private final CarResponseDtoMapper carResponseDtoMapper;
    private final CarRequestMapper carRequestMapper;

    public CarController(CarService carService, CarResponseDtoMapper carResponseDtoMapper, CarRequestMapper carRequestMapper) {
        this.carService = carService;
        this.carResponseDtoMapper = carResponseDtoMapper;
        this.carRequestMapper = carRequestMapper;
    }

    @GetMapping
    public List<CarResponseDto> findAll(){
        return carService.findAll().stream().map(
                car -> carResponseDtoMapper.toDto(car)
        ).toList();
    }
    @PostMapping
    public CarResponseDto save(CarRequestDto car){
        var response = carService.save(carRequestMapper.toEntity(car));
        return carResponseDtoMapper.toDto(response);
    }

    @GetMapping("{id}")
    public CarResponseDto findById(@PathVariable("id") Long id){
        var response = carResponseDtoMapper.toDto(carService.findById(id));
        return response;
    }
    @GetMapping("/categories")
    public List<Category> getCategories(){
        return List.of(Category.values());
    }
    @GetMapping("/available")
    public List<CarResponseDto> getAvailable(){
        return carService.findAllAvailable().stream().map(
                car -> carResponseDtoMapper.toDto(car)
        ).toList();
    }

}
