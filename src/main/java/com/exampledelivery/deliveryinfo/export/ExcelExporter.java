package com.exampledelivery.deliveryinfo.export;


import com.exampledelivery.deliveryinfo.dto.LogisticsInfoDTO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ExcelExporter {

    public void exportToExcel(List<LogisticsInfoDTO> logisticsInfoList, String filePath) throws IOException {
        Workbook workbook;
        Sheet sheet;

        File file = new File(filePath);

        if (file.exists()) {
            FileInputStream inputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(inputStream);
            sheet = workbook.getSheetAt(0); // Giả sử chỉ có một sheet
            inputStream.close();
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Logistics Information");
        }

        int lastRowNum = sheet.getLastRowNum();
        int rowNum = lastRowNum == 0 ? 0 : lastRowNum + 1; // Nếu không có dữ liệu, bắt đầu từ dòng 0, nếu có dữ liệu thì bắt đầu từ dòng tiếp theo
        for (LogisticsInfoDTO logisticsInfo : logisticsInfoList) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(logisticsInfo.getFfmPartners().get(0).getId());
            row.createCell(1).setCellValue(logisticsInfo.getFfmPartners().get(0).getName());
            row.createCell(2).setCellValue(logisticsInfo.getFfmPartners().get(0).getName());
            row.createCell(3).setCellValue(logisticsInfo.getLmPartners().get(0).getId());
            row.createCell(4).setCellValue(logisticsInfo.getLmPartners().get(0).getName());
            row.createCell(5).setCellValue(logisticsInfo.getFfmPartners().get(0).getName());
            row.createCell(6).setCellValue(logisticsInfo.getWarehouses().get(0).getId());
            row.createCell(7).setCellValue(logisticsInfo.getWarehouses().get(0).getName());
            row.createCell(8).setCellValue(logisticsInfo.getId());
            row.createCell(9).setCellValue(logisticsInfo.getProvinceName());
            row.createCell(10).setCellValue(logisticsInfo.getDistrictName());
            row.createCell(11).setCellValue(logisticsInfo.getSubdistrictName());
        }

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        }

        workbook.close();
    }

}
