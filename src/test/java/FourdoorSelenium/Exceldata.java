package FourdoorSelenium;


import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;

public class Exceldata {

    @DataProvider(name = "fourdoor")
    public static Object[][] getfourdoordata() throws IOException {
        return Exceldata.excelFourDoorAnalysis("Sheet1");
    }

//    public static void main(String[] args) throws IOException {
//        excelFourDoorAnalysis();
//    }

    public static XSSFSheet sheet;
    public static XSSFWorkbook workbook;
    public static FileInputStream ip;


    public static Object[][] excelFourDoorAnalysis(String sheet1) throws IOException {
        ip = new FileInputStream("data/test.xlsx");
        workbook = new XSSFWorkbook(ip);
        sheet = workbook.getSheet("Sheet1");

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][cols];
        for (int i = 0; i < rows; i++) {
            XSSFRow row = sheet.getRow(i + 1);
            for (int j = 0; j < cols; j++) {
                XSSFCell cell = row.getCell(j);
                CellType cellType = cell.getCellType();
                switch (cellType){
                    case STRING -> {
                        data[i][j] = cell.getStringCellValue();
                        break;
                    }
                }

            }
        }
        return data ;

    }
}