package org.utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelReadWriteUtil {

    WebDriver driver;
    public ExcelReadWriteUtil(WebDriver driver) {
    }

    public static String readExcel(String filePath, int sheetIndex, int row, int col) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(sheetIndex);

        DataFormatter dataFormatter = new DataFormatter(new java.util.Locale("en", "US"));
        //DataFormatter can set to use cached values for formula cells
        dataFormatter.setUseCachedValuesForFormulaCells(true);

        String cellValue = dataFormatter.formatCellValue(sheet.getRow(row).getCell(col));
        //System.out.println(cellValue);
        return cellValue;
    }

    public static void writeExcel(String filePath, int sheetIndex, int row, int col, String data) throws IOException {

        try {
            FileInputStream fip = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(fip);
            XSSFSheet sheet = workbook.getSheetAt(sheetIndex);

            XSSFCell cell = sheet.getRow(row).createCell(col);

            cell.setCellValue(data);

            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            //fos.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeInExcel(String filePath, int sheetIndex, int row, int col, String data) throws IOException {

        try {
            FileInputStream fip = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(fip);
            XSSFSheet sheet = workbook.getSheetAt(sheetIndex);

            Row empRow = sheet.createRow(row);
            Cell c1 = empRow.createCell(col);
            c1.setCellValue(data);

            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            //fos.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
