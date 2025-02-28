package com.app.controller.bulk_upload;

import com.app.entity.cars.Brand;
import com.app.repository.carRepo.BrandRepository;
import com.app.service.BulkUploadBrandNameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/brand/bulk-upload")
public class BrandBulkUploadController {
    private final BulkUploadBrandNameService bulkUploadBrandService;
    private final BrandRepository brandRepository;

    public BrandBulkUploadController(BulkUploadBrandNameService bulkUploadBrandService, BrandRepository brandRepository) {
        this.bulkUploadBrandService = bulkUploadBrandService;
        this.brandRepository = brandRepository;
    }

    @PostMapping("/upload")
    public void uploadBrands(@RequestParam("filepath") String filepath) throws IOException {
        List<Brand> brandList = bulkUploadBrandService.readExcelData(filepath);
        brandRepository.saveAll(brandList);
    }
}
