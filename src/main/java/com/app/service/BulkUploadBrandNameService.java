package com.app.service;

import com.app.entity.cars.Brand;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BulkUploadBrandNameService {

    public List<Brand> readExcelData(String filePath) throws IOException {
        List<Brand> brands = new ArrayList<>();

        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        // Skip the header row
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Brand brand = new Brand();

            if (row.getCell(0) != null) {
                brand.setId((long) row.getCell(0).getNumericCellValue());
            }

            if (row.getCell(1) != null) {
                brand.setBrandName(row.getCell(1).getStringCellValue());
            }

            brands.add(brand);
        }

        workbook.close();
        fis.close();

        return brands;
    }
}
