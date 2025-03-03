package com.app.controller.car;

import com.app.entity.cars.*;
import com.app.payload.carDto.*;
import com.app.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/addBrand")
    public ResponseEntity<?> addCarBrand(@RequestBody BrandDto brandDto){
        String status = carService.addCarBrand(brandDto);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @PostMapping("/addFuelType")
    public ResponseEntity<?> addFuelType(@RequestBody FuelTypeDto fuelTypeDto){
        String status = carService.addFuelType(fuelTypeDto);
        return new ResponseEntity<>(status,HttpStatus.CREATED);
    }

    @PostMapping("/addTransmission")
    public ResponseEntity<?> addTransmissionType(@RequestBody TransmissionDto transmissionDto){
        String status = carService.addTransmissionType(transmissionDto);
        return new ResponseEntity<>(status,HttpStatus.CREATED);
    }

    @PostMapping("/addModel")
    public ResponseEntity<?> addCarModel(@RequestBody ModelDto modelDto){
        String status = carService.addCarModel(modelDto);
        return new ResponseEntity<>(status,HttpStatus.CREATED);
    }

    @PostMapping("/addYear")
    public ResponseEntity<?> addYear(@RequestBody YearDto yearDto){
        String status = carService.addYear(yearDto);
        return new ResponseEntity<>(status,HttpStatus.CREATED);
    }

    @PostMapping("/addCar")
    public ResponseEntity<?> addCar(@RequestBody CarDto carDto){
        String status = carService.addCar(carDto);
        return new ResponseEntity<>(status,HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Car>> getAllCar(){
        List<Car> carList = carService.getAllCar();
        return new ResponseEntity<>(carList,HttpStatus.OK);
    }

    @GetMapping("/get-byParam")
    public ResponseEntity<List<Car>> getByName(@RequestParam String param){
        List<Car> carList = carService.getByName(param);
        return new ResponseEntity<>(carList,HttpStatus.OK);
    }

    @PostMapping("/add-Status")
    public ResponseEntity<String> addStatus(@RequestBody CarStatusDto carStatusDto){
        String status = carService.addCarStatus(carStatusDto);
        return new ResponseEntity<>(status,HttpStatus.CREATED);
    }

    @GetMapping("/get-cars-by-status")
    public ResponseEntity<List<Car>> getCarsByStatus(@RequestParam String status){
        List<Car> carList = carService.getCarsByStatus(status);
        return new ResponseEntity<>(carList,HttpStatus.OK);
    }

    @PutMapping("/book-car")
    public ResponseEntity<String> bookCar(@RequestParam Long carId){
        String status = carService.bookCar(carId);
        return new ResponseEntity<>(status,HttpStatus.OK);
    }

    @GetMapping("/get-cars-by-brands")
    public ResponseEntity<List<Car>> getCarsByBrand(@RequestParam List<String> brands){
        List<Car> carList = carService.getCarsByBrands(brands);
        return new ResponseEntity<>(carList,HttpStatus.OK);
    }

}
