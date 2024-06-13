package utils;


import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;


public class ExcelUtils {

    public static XSSFSheet readExcel(String excelPath, String sheetName) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(excelPath);
        return workbook.getSheet(sheetName);

    }


public static String getCellValue(XSSFSheet sheet, int rowIndex, int colIndex)
{
    DataFormatter formatter = new DataFormatter();
    return formatter.formatCellValue(sheet.getRow(rowIndex).getCell(colIndex));
}




}
