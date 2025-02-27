package com.app.controller.image;

import com.app.entity.cars.Car;
import com.app.entity.cars.CarImage;
import com.app.entity.evaluation.CarEvaluationPhotos;
import com.app.repository.carRepo.CarImageRepository;
import com.app.repository.carRepo.CarRepository;
import com.app.repository.evaluation.CarEvaluationPhotosRepository;
import com.app.service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/actual-car-photos")
public class ActualCarPhotos {
    private final BucketService bucketService;
    private final CarRepository carRepo;
    private final CarEvaluationPhotosRepository carEvaPhoRepo;

    @Autowired
    public ActualCarPhotos(BucketService bucketService, CarRepository carRepo, CarEvaluationPhotosRepository carEvaPhoRepo) {
        this.bucketService = bucketService;
        this.carRepo = carRepo;
        this.carEvaPhoRepo = carEvaPhoRepo;
    }

    @PostMapping("/upload/file/{bucketName}/{carId}")
    public ResponseEntity<List<CarEvaluationPhotos>> uploadMultiplePhotosOfCar(@RequestParam List<MultipartFile> files,
                                                                    @PathVariable String bucketName,
                                                                    @PathVariable Long carId){
        Car car = carRepo.findById(carId).orElseThrow(()->new RuntimeException("Car with given id is not present : "+carId));
        List<CarEvaluationPhotos> images = new ArrayList<>();
        for(MultipartFile file : files){
            String url = bucketService.uploadFile(file, bucketName);
            System.out.println(url);
            CarEvaluationPhotos image = new CarEvaluationPhotos();
            image.setPhotoUrl(url);
            System.out.println("Image object : "+image.getPhotoUrl());
            images.add(image);
            carEvaPhoRepo.save(image);
        }
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

}
