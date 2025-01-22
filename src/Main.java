/*
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;

    public class Main {
        public static void main(String[] args) throws IOException {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Test");

            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("Hello");
            row.createCell(1).setCellValue("World");

            try (FileOutputStream fileOut = new FileOutputStream("test.xlsx")) {
                workbook.write(fileOut);
            }

            System.out.println("Excel file created successfully.");
            workbook.close();
        }
    }*/
