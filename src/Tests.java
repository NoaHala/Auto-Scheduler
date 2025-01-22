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

        System.out.println("SUCCESS!");
        System.out.println("output sanity check:");
        System.out.println(timeSlotsTable.toString());

        // Create Excel parser and initialize student set
        System.out.print("creating excel parser and students hash set...");
        ExcelParser excelParser = new ExcelParser();
        HashSet<Student> nhs = new HashSet<>();
        System.out.println("SUCCESS!");

        // Parse Excel data to the DS of the students and the time slots
        System.out.println("using the excel parser with the students hash set and time slots table...");
        excelParser.studentsExcelParser(nhs,timeSlotsTable.getTimeSlots(),"example1.xlsx");
        System.out.println("SUCCESS!");

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

        // Loop through all the time slots and choose the student for them



    }

    private static TimeSlotsTable createTimeSlotsTable() {
        TimeSlotsTable timeSlotsTable = new TimeSlotsTable();
        timeSlotsTable.createTimeSlots(Day.SUNDAY,"06:30","12:00", 45);
        timeSlotsTable.createTimeSlots(Day.MONDAY,"06:30","12:00", 45);
        timeSlotsTable.createTimeSlots(Day.TUESDAY,"06:30","12:00", 45);
        timeSlotsTable.createTimeSlots(Day.WEDNESDAY,"06:30","12:00", 45);
        timeSlotsTable.createTimeSlots(Day.THURSDAY,"06:30","12:00", 45);
        timeSlotsTable.createTimeSlots(Day.FRIDAY,"06:30","12:00", 45);

        return timeSlotsTable;
    }


}
