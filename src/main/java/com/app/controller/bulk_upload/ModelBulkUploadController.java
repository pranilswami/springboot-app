package com.app.controller.bulk_upload;

import com.app.entity.cars.Model;
import com.app.repository.carRepo.ModelRepository;
import com.app.service.bulk_upload_services.BulkUploadModelNameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/model/bulk-upload")
public class ModelBulkUploadController {
    private final BulkUploadModelNameService bulkUploadModelService;
    private final ModelRepository modelRepository;

    public ModelBulkUploadController(BulkUploadModelNameService bulkUploadModelService, ModelRepository modelRepository) {
        this.bulkUploadModelService = bulkUploadModelService;
        this.modelRepository = modelRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadModels(@RequestParam("filepath") String filepath) throws IOException {
        List<Model> modelList = bulkUploadModelService.readExcelData(filepath);
        modelRepository.saveAll(modelList);
        return new ResponseEntity<>("Models added successfully from xml to database!", HttpStatus.CREATED);
    }
}
