package utils;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;


public class ExcelUtils {

    private static XSSFWorkbook workbook;

    public static XSSFSheet readExcel(String excelPath, String sheetName) throws IOException {

         workbook = new XSSFWorkbook(excelPath);
        return workbook.getSheet(sheetName);

    }


public static String getCellValue(XSSFSheet sheet, int rowIndex, int colIndex)
{
   /* DataFormatter formatter = new DataFormatter();
    return formatter.formatCellValue(sheet.getRow(rowIndex).getCell(colIndex));*/
    FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
    Cell cell = sheet.getRow(rowIndex).getCell(colIndex);
    if (cell == null) {
        return "";
    }
    switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
        case STRING:
            return cell.getStringCellValue();
        case NUMERIC:
            if (DateUtil.isCellDateFormatted(cell)) {
                return cell.getDateCellValue().toString();
            } else {
                return String.valueOf((int) cell.getNumericCellValue());
            }
        case BOOLEAN:
            return String.valueOf(cell.getBooleanCellValue());
        case FORMULA:
            return String.valueOf(formulaEvaluator.evaluate(cell).getNumberValue());
        default:
            return "";
    }

}




}
