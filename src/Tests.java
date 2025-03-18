import com.google.ortools.Loader;
import com.google.ortools.graph.MinCostFlow;
import com.google.ortools.graph.MinCostFlowBase;

import java.util.HashSet;


/**
 * The main entry point for testing the scheduling program.
 * This class initializes the time slots table, reads student data from an Excel file,
 * and performs sanity checks on the data structures.
 */

public class Tests {
    public static void main(String[] args) {

        // Initialize time slots table and populate it with time slots
        System.out.print("creating time slots table...");
        TimeSlotsTable timeSlotsTable = createTimeSlotsTable();

        //todo - currently works only with 09:00

        System.out.println("output sanity check:");
        System.out.println(timeSlotsTable.toString());

        // Create Excel parser and initialize student set
        System.out.print("creating excel parser and students hash set");
        ExcelParser excelParser = new ExcelParser();
        HashSet<Student> nhs = new HashSet<>();

        // Parse Excel data to the DS of the students and the time slots
        System.out.println("using the excel parser with the students hash set and time slots table...");
        excelParser.studentsExcelParser(nhs,timeSlotsTable.getTimeSlots(),"example1.xlsx");

        // Print sanity checks for the students DS
        System.out.println("Students hash set sanity check:");
        for(Student student:nhs){
            System.out.println("Student Name: " + student.getName() +
                    ", VehicleType : " + student.getVehicle()+
                    ", priority : " + student.getPriority());
        }

        // Print sanity checks for the time slots table DS
        System.out.println("time slots table hash map sanity check:");
        System.out.println(timeSlotsTable.toString());

        // Sort the time slot list
        timeSlotsTable.sortTimeSlotsByStudentsCounter();

        // Print sanity checks after sorted
        System.out.println("time slots sorted sanity check:");
        System.out.println(timeSlotsTable.getListTimeSlots().toString());

        // Loop through all the time slots and choose the student for them
        //inside time slot table i need a func that loop over the slots
        //inside time slots i need a funk that choose the student with the least amount of available time slots

        //check using in the new or-tools library
        try {
            // טוען את הספריות של OR-Tools
            Loader.loadNativeLibraries();
            System.out.println("OR-Tools נטען בהצלחה!");

            // יצירת אובייקט MinCostFlow כדי לוודא שהכל עובד
            MinCostFlow minCostFlow = new MinCostFlow();
            System.out.println("✅ הצלחנו ליצור MinCostFlow!");

        } catch (UnsatisfiedLinkError e) {
            System.err.println(" שגיאה: OR-Tools לא נטען כראוי!");
            e.printStackTrace();
        }

        System.out.print("00000000000000000000");
    }

    private static TimeSlotsTable createTimeSlotsTable() {
        TimeSlotsTable timeSlotsTable = new TimeSlotsTable();
        timeSlotsTable.createTimeSlots(Day.SUNDAY,"06:30","12:00", 45);

/*
        timeSlotsTable.createTimeSlots(Day.MONDAY,"06:30","12:00", 45);
        timeSlotsTable.createTimeSlots(Day.TUESDAY,"06:30","12:00", 45);
        timeSlotsTable.createTimeSlots(Day.WEDNESDAY,"06:30","12:00", 45);
        timeSlotsTable.createTimeSlots(Day.THURSDAY,"06:30","12:00", 45);
        timeSlotsTable.createTimeSlots(Day.FRIDAY,"06:30","12:00", 45);
*/


        return timeSlotsTable;
    }


}

