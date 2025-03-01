package com.app.controller.image;

import com.app.entity.cars.Car;
import com.app.entity.cars.CarImage;
import com.app.repository.carRepo.CarImageRepository;
import com.app.repository.carRepo.CarRepository;
import com.app.service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.Optional;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {
    private final BucketService bucketService;
    private final CarRepository carRepo;
    private final CarImageRepository imageRepo;

    @Autowired
    public ImageController(BucketService bucketService, CarRepository carRepo, CarImageRepository imageRepo) {
        this.bucketService = bucketService;
        this.carRepo = carRepo;
        this.imageRepo = imageRepo;
    }

    @PostMapping("/upload/file/{bucketName}/{carId}")
    public ResponseEntity<CarImage> uploadCarPhotos(@RequestParam MultipartFile file,
                                                    @PathVariable String bucketName,
                                                    @PathVariable Long carId) {
        String url = bucketService.uploadFile(file, bucketName);
        Optional<Car> opCar = carRepo.findById(carId);
        CarImage image = new CarImage();
        if (opCar.isPresent()) {
            Car car = opCar.get();
            image.setUrl(url);
            image.setCar(car);
            imageRepo.save(image);
        }
        else{
            throw new NullPointerException("Car is not present in database with given id");
        }
        return new ResponseEntity<>(image, HttpStatus.OK);
    }


}
