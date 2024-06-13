package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelUtils {

    public static void main(String[] args) {
        readExcel();
    }

    public static void readExcel()
    {
        try {
            String excelPath = "data/FourDoorMigrationContentV1 .xlsx";

            XSSFWorkbook workbook = new XSSFWorkbook(excelPath);
            XSSFSheet sheet = workbook.getSheet("Super_Category");
             //int rowCount = sheet.getPhysicalNumberOfRows();
            int rowCount = sheet.getPhysicalNumberOfRows();
            System.out.println(rowCount);
             int colCount = sheet.getRow(0).getPhysicalNumberOfCells();


            DataFormatter formatter = new DataFormatter();

             for (int i =1; i<=rowCount; i++)
             {
                 for(int j = 0; j< colCount; j++)
                 {
                     Object value = formatter.formatCellValue(sheet.getRow(i).getCell(0));
                     System.out.println(value);

                 }
             }


        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
