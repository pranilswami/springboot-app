package com.app.service.bulkUpload;

import com.app.entity.cars.Model;
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
public class BulkUploadModelNameService {

    public List<Model> readExcelData(String filepath) throws IOException {
        List<Model> modelList = new ArrayList<>();

        FileInputStream fis = new FileInputStream(filepath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        if(rowIterator.hasNext()){
            rowIterator.next();
        }

        while(rowIterator.hasNext()){
            Row row = rowIterator.next();
            Model model = new Model();

            if(row.getCell(0)!=null){
                model.setId((long) row.getCell(0).getNumericCellValue());
            }

            if(row.getCell(1)!=null){
                model.setModelName(row.getCell(1).getStringCellValue());
            }
            modelList.add(model);
        }
        workbook.close();
        fis.close();
        return modelList;
    }
}
