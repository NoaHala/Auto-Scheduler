import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;


/**
 * A utility class for extracting and parsing data from Excel files.
 */

public class ExcelParser {

    // todo - for now we assume that all the info about a student is in one excel table
    // todo - we also ignore changes about the info from the teacher

    /**
     * A method that extract all the relevant information about the students and their available
     * time slots from the Excel table into designated data structures.
     *
     * @param studentSet - the schedule's student set.
     * @param timeSlotsTable - the schedule's timeSlots Table map.
     * @param filePath - the path for the student excel.
     */

    public void studentsExcelParser(HashSet<Student> studentSet,
                                    HashMap<DayHourKey, TimeSlot> timeSlotsTable,
                                    String filePath) {
        try {
            // Create a file input stream to read the Excel file
            FileInputStream fis = new FileInputStream(new File(filePath));

            // Create a workbook instance for XSSFWorkbook (for .xlsx files)
            Workbook workbook = new XSSFWorkbook(fis);

            // Get the first sheet of the workbook
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate over the rows in the sheet, skipping the header row
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                // if the current row is empty skip it
                if (row == null) continue;

                // Extract student data
                String fullName = row.getCell(0).getStringCellValue();
                String vehicle = row.getCell(1).getStringCellValue();
                int status = (int) row.getCell(2).getNumericCellValue();

                //todo- change the vehicle
                Student student = new Student(fullName, new AutoCar(), status);
                studentSet.add(student);

                //parse the time slots string
                timeSlotsStringParser(row, timeSlotsTable, student);
            }
        } catch (IOException e) {
            System.out.println("Error - a problem occurred while processing the Excel file at path: " + filePath);
        }
    }


    /**
     * Parses a row from the Excel sheet to extract a student's time slot availability
     * and updates the provided `timeSlotsTable` accordingly.
     *
     * This method takes a single row from the Excel sheet and checks each day's
     * availability column. It parses the time ranges provided and matches them
     * to the corresponding `TimeSlot` objects in the `timeSlotsTable`. The student
     * is then added to the appropriate time slots.
     *
     * @param row             The Excel row containing the student's availability data.
     * @param timeSlotsTable  A map linking `DayHourKey` to `TimeSlot`, representing the schedule.
     * @param student         The `Student` object to be added to the relevant time slots.
     */

    private void timeSlotsStringParser(Row row, HashMap<DayHourKey, TimeSlot> timeSlotsTable, Student student){
        // Map each day's availability
        for (int col = 3; col < row.getLastCellNum(); col++) {
            Day day = Day.values()[col - 3]; // Map column index to Day enum
            String availability = row.getCell(col).getStringCellValue();

            // Parse availability times and add student to TimeSlot
            for (String timeRange : availability.split(",")) {
                String[] times = timeRange.trim().split("-");
                if (times.length == 2) {
                    String startTime = times[0].trim();
                    //String endTime = times[1].trim();

                    DayHourKey key = new DayHourKey(day, startTime);
                    TimeSlot timeSlot = timeSlotsTable.get(key);

                    if (timeSlot != null && timeSlot.getBeginningHour().equals(startTime)) {
                        timeSlot.addAvailableStudent(student);
                        student.addAvailableTimeSlot(timeSlot);
                        //student.addAvailableSlot();
                    }
                }
            }
        }
    }
}

