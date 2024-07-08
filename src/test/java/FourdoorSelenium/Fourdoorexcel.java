package FourdoorSelenium;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fourdoorexcel {

    public List<Map<String, Object>> fetchExcelData(String sheetName) throws IOException {
        FileInputStream ip = new FileInputStream("data/metaexcel.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(ip);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        List<Map<String, Object>> excelDataList = new ArrayList<>();

        int rows = sheet.getLastRowNum();
        XSSFRow headerRow = sheet.getRow(0);
        int cols = headerRow.getLastCellNum();

        // Iterate through each row (starting from 1 to skip header row)
        for (int i = 1; i <= rows; i++) {
            XSSFRow currentRow = sheet.getRow(i);
            Map<String, Object> rowData = new HashMap<>();

            // Iterate through each column
            for (int j = 0; j < cols; j++) {
                XSSFCell cell = currentRow.getCell(j);
                String header = headerRow.getCell(j).getStringCellValue();
                Object cellValue = getCellValue(cell);
                if (header.equals("name") || header.equals("title") || header.equals("h1") || header.equals("meta_description")) {
                    rowData.put(header, cellValue);
                }
            }

            excelDataList.add(rowData);
        }

        workbook.close();
        ip.close();

        return excelDataList;
    }

    private static Object getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return cell.getNumericCellValue(); // Handle numeric values
            default:
                return null;
        }
    }
}
