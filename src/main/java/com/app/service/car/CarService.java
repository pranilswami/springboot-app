package com.app.service.car;

import com.app.entity.cars.*;
import com.app.exception.ResourceNotFoundException;
import com.app.payload.carDto.*;
import com.app.repository.carRepo.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@Service
public class CarService {
    private final BrandRepository brandRepo;
    private final ModelMapper modelMapper;
    private final FuelTypeRepository fuelTypeRepo;
    private final TransmissionRepository transmissionRepo;
    private final ModelRepository modelRepo;
    private final YearRepository yearRepo;
    private final CarRepository carRepo;
    private final CarStatusRepository carStatusRepository;

    public CarService(BrandRepository brandRepo, ModelMapper modelMapper, FuelTypeRepository fuelTypeRepo, TransmissionRepository transmissionRepo, ModelRepository modelRepo, YearRepository yearRepo, CarRepository carRepo, CarStatusRepository carStatusRepository) {
        this.brandRepo = brandRepo;
        this.modelMapper = modelMapper;
        this.fuelTypeRepo = fuelTypeRepo;
        this.transmissionRepo = transmissionRepo;
        this.modelRepo = modelRepo;
        this.yearRepo = yearRepo;
        this.carRepo = carRepo;
        this.carStatusRepository = carStatusRepository;
    }

    public String addCarBrand(BrandDto brandDto){
        Optional<Brand> opBrand = brandRepo.findByBrandName(brandDto.getBrand());
        if(opBrand.isPresent()){
            throw new RuntimeException("BRAND NAME IS ALREADY EXISTS");
        }
        Brand brand = new Brand();
        brand.setBrandName(brandDto.getBrand());
        brandRepo.save(brand);
        return "Car Brand added successfully";
    }

    public String addFuelType(FuelTypeDto fuelTypeDto) {
        Optional<FuelType> opFuelType = fuelTypeRepo.findByFuelType(fuelTypeDto.getFuelType());
        if(opFuelType.isPresent()){
            throw new RuntimeException("FUEL TYPE IS ALREADY EXISTS");
        }
        FuelType fuelType = new FuelType();
        fuelType.setFuelType(fuelTypeDto.getFuelType());
        fuelTypeRepo.save(fuelType);
        return "Fuel type added successfully";
    }

    public String addTransmissionType(TransmissionDto transmissionDto) {
        Optional<Transmission> opTransmission = transmissionRepo.findByTransmissionType(transmissionDto.getFuelType());
        if (opTransmission.isPresent()){
            throw new RuntimeException("TRANSMISSION TYPE IS ALREADY EXISTS");
        }
        Transmission transmission = new Transmission();
        transmission.setType(transmissionDto.getFuelType());
        transmissionRepo.save(transmission);
        return "Transmission Type added successfully";
    }

    public String addCarModel(ModelDto modelDto) {
        Optional<Model> opModel = modelRepo.findByModelName(modelDto.getModelName());
        if (opModel.isPresent()){
            throw new RuntimeException("MODEL IS ALREADY EXISTS");
        }
        Model model = new Model();
        model.setModelName(modelDto.getModelName());
        modelRepo.save(model);
        return "Car Model added successfully";
    }

    public String addYear(YearDto yearDto) {
        Optional<Year> opYear = yearRepo.findByYear(yearDto.getYear());
        if (opYear.isPresent()){
            throw new RuntimeException("YEAR IS ALREADY EXISTS");
        }
        Year year = new Year();
        year.setYear(yearDto.getYear());
        yearRepo.save(year);
        return "Year added successfully";
    }

    Brand convertToEntity(BrandDto brandDto){
        return modelMapper.map(brandDto, Brand.class);
    }

    public String addCar(CarDto carDto){

        Brand brand = brandRepo.findById(carDto.getBrandId()).orElseThrow(()->new ResourceNotFoundException("Brand not found"));
        Model model = modelRepo.findById(carDto.getModelId()).orElseThrow(()->new ResourceNotFoundException("Model not found"));
        Transmission transmission = transmissionRepo.findById(carDto.getTransmissionId()).orElseThrow(()->new ResourceNotFoundException("Transmission not found"));
        FuelType fuelType = fuelTypeRepo.findById(carDto.getFuelTypeId()).orElseThrow(()->new ResourceNotFoundException("FuelType not found"));
        Year year = yearRepo.findById(carDto.getYearId()).orElseThrow(()->new ResourceNotFoundException("Year not found"));
        CarStatus status = carStatusRepository.findById(1L).orElseThrow(()->new RuntimeException("Status not found"));

        Car car = new Car();
        car.setBrand(brand);
        car.setFuelType(fuelType);
        car.setYear(year);
        car.setModel(model);
        car.setTransmission(transmission);
        car.setCarStatus(status);
        carRepo.save(car);
        return "Car added successfully";
    }

    public List<Car> getAllCar() {
        return carRepo.findAll();
    }

    public List<Car> getByName(String param) {
        try{
            int year = Integer.parseInt(param);
            return carRepo.findCarsByParam(year);
        }catch (NumberFormatException e){
            return carRepo.findCarsByParam(param);
        }
    }

    public String addCarStatus(CarStatusDto carStatusDto) {

        Optional<CarStatus> opCarstatus = carStatusRepository.findByName(carStatusDto.getStatus());
        if(opCarstatus.isPresent()){
            throw new RuntimeException("STATUS IS ALREADY EXISTS");
        }
        CarStatus carStatus = new CarStatus();
        carStatus.setStatus(carStatusDto.getStatus());
        carStatusRepository.save(carStatus);
        return "Car Status added successfully!";
    }

    public List<Car> getCarsByStatus(String status) {
        return carRepo.findCarIdsByStatus(status);
    }

    public String bookCar(Long carId) {
        Car car = carRepo.findById(carId).orElseThrow(()->new RuntimeException("Car not found with given id"));
        CarStatus status = carStatusRepository.findById(2L).orElseThrow(()->new RuntimeException("Status not found"));
        car.setCarStatus(status);
        carRepo.save(car);
        return "Car booked successfully!";
    }

    public List<Car> getCarsByBrands(List<String> brands) {
        return carRepo.findByBrandNameIN(brands,1L);
    }
}
