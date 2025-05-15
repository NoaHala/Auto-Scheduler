/*
import com.google.ortools.Loader;
import com.google.ortools.graph.MinCostFlow;
import com.google.ortools.graph.MinCostFlowBase;

import java.util.*;


*/
/**
 * The main entry point for testing the scheduling program.
 * This class initializes the time slots table, reads student data from an Excel file,
 * and performs sanity checks on the data structures.
 *//*


public class Tests {
    private static final long LARGE_COST = 100;

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
        HashSet<Student> studentHashSet = new HashSet<>();

        // Parse Excel data to the DS of the students and the time slots
        System.out.println("using the excel parser with the students hash set and time slots table...");
        excelParser.studentsExcelParser(studentHashSet,timeSlotsTable.getTimeSlots(),"example1.xlsx");

        // Print sanity checks for the students DS
        System.out.println("Students hash set sanity check:");
        for(Student student:studentHashSet){
            System.out.println("Student Name: " + student.getName() +
                    ", VehicleType : " + student.getVehicle()+
                    ", priority : " + student.getPriority());
        }

        //print sanity check for the students available slots
        System.out.println("Students available slots:");
        for(Student student:studentHashSet){
            System.out.println("Student Name: " + student.getName());
            System.out.println(student.getAvailableTimeSlots());
        }


*/
/*        // Print sanity checks for the time slots table DS
        System.out.println("time slots table hash map sanity check:");
        System.out.println(timeSlotsTable.toString());*//*


        //use the data to build the minCost-maxFlow network
        createMinCostMaxFlowNetwork(studentHashSet ,timeSlotsTable);

        // print the result
        System.out.println("schedule results:");
        System.out.println(timeSlotsTable.toString());



*/
/*        // Sort the time slot list
        timeSlotsTable.sortTimeSlotsByStudentsCounter();

        // Print sanity checks after sorted
        System.out.println("time slots sorted sanity check:");
        System.out.println(timeSlotsTable.getListTimeSlots().toString());*//*


        // Loop through all the time slots and choose the student for them
        //inside time slot table i need a func that loop over the slots
        //inside time slots i need a funk that choose the student with the least amount of available time slots

*/
/*        //check using in the new or-tools library
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
        }*//*


        printScheduleTable(timeSlotsTable);

        System.out.print("00000000000000000000");
    }

    private static void createMinCostMaxFlowNetwork(HashSet<Student> studentsSet, TimeSlotsTable timeSlotsTable){
        // load the OR-Tools library:
        Loader.loadNativeLibraries();

        */
/****** build the network ******//*


        // create minCostFlow Network:
        MinCostFlow network = new MinCostFlow();

        */
/****** build the network's nodes ******//*


        // create the source node
        int idCounter = 0;
        int source = idCounter++;


        // create the students nodes
        HashMap<Student, Integer> studentAndNodeID = new HashMap<>();
        for (Student student: studentsSet) {
            studentAndNodeID.put(student, idCounter++);
            //System.out.println("student node: " + (idCounter-1)+ " - " + student.getName() );
        }

        // create the studentDayKey nodes
        // notice - this is an additional layer that helps to enforce the
        // constraint of "a single student cant get more than one lesson in the same day"
        HashMap<StudentDayKey, Integer> studentDayAndNodeID = new HashMap<>();
        HashMap<Integer, StudentDayKey> nodeToStudentDayKey = new HashMap<>();
        for (Student student: studentsSet) {
            for(Day day: Day.values()) {
                StudentDayKey studentDay = new StudentDayKey(student, day);
                nodeToStudentDayKey.put(idCounter, studentDay);
                studentDayAndNodeID.put(studentDay, idCounter++);
                //System.out.println("studentDay node: " +  (idCounter-1) + " - " + studentDay.getStudent().getName() + studentDay.getDay());
            }
        }

        // create the time slots nodes
        HashMap<TimeSlot, Integer> timeSlotAndNodeID = new HashMap<>();
        HashMap<Integer, TimeSlot> nodeToSlot = new HashMap<>();

        for (TimeSlot timeSlot: timeSlotsTable.getListTimeSlots()) {
            nodeToSlot.put(idCounter, timeSlot);
            timeSlotAndNodeID.put(timeSlot, idCounter++);
            //System.out.println("time slot node: " +  (idCounter-1) + " - "+ timeSlot.getBeginningHour());
        }

        // create the sink node
        int sink = idCounter++;
        int virtualSink = idCounter++;

        */
/****** build the network's arcs ******//*


        // define the indexes of the arcs that we'll retrieve later
        int arcID = 0;
        int firstTimeSlotArc, lastTimeSlotArc;


        // create the arcs from the source to the students
        for (Student student: studentsSet) {
            network.addArcWithCapacityAndUnitCost(source,studentAndNodeID.get(student), 1, 0);
            //System.out.println("source to student arc1: " +  arcID + " - "+ student.getName());
            arcID++;
            network.addArcWithCapacityAndUnitCost(source,studentAndNodeID.get(student), 1, 5);
            //System.out.println("source to student arc2: " +  arcID + " - "+ student.getName());
            arcID++;
        }

        // create the arcs from the student nodes to the studentDayKey nodes
        for (Student student: studentsSet) {
            int studentNodeID = studentAndNodeID.get(student);
            for(Day day: Day.values()) {
                StudentDayKey studentDayKey = new StudentDayKey(student, day);
                int studentDayNodeID =  studentDayAndNodeID.get(studentDayKey);
                network.addArcWithCapacityAndUnitCost(studentNodeID, studentDayNodeID, 2, 0);
                //System.out.println("student to studentDay arc: " +  arcID + " - "+ student.getName() + studentDayKey.getStudent().getName() + studentDayKey.getDay().toString());
                arcID++;
            }
        }

        // helpers to retrive those arcs in the future
        firstTimeSlotArc = arcID;

        // create the arcs from the student-day nodes to time slot nodes
        for (Student student: studentsSet){
            for (TimeSlot timeSlot: student.getAvailableTimeSlots()){
                StudentDayKey studentDayKey = new StudentDayKey(student, timeSlot.getDay());
                int studentDayNodeID = studentDayAndNodeID.get(studentDayKey);
                int timeSlotID = timeSlotAndNodeID.get(timeSlot);
                network.addArcWithCapacityAndUnitCost(studentDayNodeID, timeSlotID,1,0);
                //System.out.println("studentday to timeslot arc: " +  arcID + " - "+ studentDayKey.getStudent().getName() + studentDayKey.getDay().toString() + timeSlot.getBeginningHour());
                arcID++;
            }
        }
        lastTimeSlotArc = arcID;


        // create the arcs from the time slots to the sink
        for (TimeSlot timeSlot: timeSlotsTable.getListTimeSlots()) {
            network.addArcWithCapacityAndUnitCost(timeSlotAndNodeID.get(timeSlot), sink,1,0);
            //System.out.println("timeslot to sink arc: " +  arcID + " - " + timeSlot.getBeginningHour());
            arcID++;
        }

        int maxSupply = 2 * studentsSet.size();
        //create the arcs to the vitrualSink
        network.addArcWithCapacityAndUnitCost(sink,virtualSink,maxSupply,0);
        network.addArcWithCapacityAndUnitCost(source, virtualSink,maxSupply,LARGE_COST);



        */
/**** run the max-flow algorithm ****//*


        // set supply
        int supply = 2 * studentsSet.size();
        network.setNodeSupply(source, supply);
        network.setNodeSupply(virtualSink,(-1)*supply);

        // create the "flow"
        MinCostFlowBase.Status resultStatus = network.solve();
        if (resultStatus != MinCostFlowBase.Status.OPTIMAL){
            System.out.println("ERROR: couldn't solve the flow " + resultStatus.toString());
            return;
        }

        */
/****** get the final result ******//*

        for (int arc = firstTimeSlotArc; arc < lastTimeSlotArc; arc++) {
            //System.out.println("arc num: " + arc);
            if(network.getFlow(arc) == 1){
                //System.out.println("has 1 flow");
                int head = network.getHead(arc);
                int tail = network.getTail(arc);
                //System.out.println("tail num: "+ tail);

                TimeSlot curSlot = nodeToSlot.get(head);
                StudentDayKey studentDayKey = nodeToStudentDayKey.get(tail);
                if (studentDayKey == null){
                    //System.out.println("studentDayKey is null");
                }
                else{
                    curSlot.setChosenStudent(studentDayKey.getStudent());
                }

            }
        }
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

    public static void printScheduleTable(TimeSlotsTable slotsTable) {
        // מיפוי: יום → רשימת טיים סלוטים באותו יום
        Map<Day, List<TimeSlot>> dayToSlots = new TreeMap<>(Comparator.comparingInt(Day::ordinal));

        for (TimeSlot ts : slotsTable.getListTimeSlots()) {
            dayToSlots.putIfAbsent(ts.getDay(), new ArrayList<>());
            dayToSlots.get(ts.getDay()).add(ts);
        }

        // אוספים את כל השעות האפשריות (בכל הימים) לפי סדר
        Set<String> allHours = new TreeSet<>();
        for (TimeSlot ts : slotsTable.getListTimeSlots()) {
            allHours.add(ts.getBeginningHour());
        }

        // הדפסת כותרת עמודות
        System.out.print(String.format("%-10s", "Day/Hour"));
        for (String hour : allHours) {
            System.out.print(String.format("%-15s", hour));
        }
        System.out.println();

        // הדפסת שורות לפי ימים
        for (Day day : Day.values()) {
            System.out.print(String.format("%-10s", day.name()));
            for (String hour : allHours) {
                DayHourKey key = new DayHourKey(day, hour);
                TimeSlot slot = slotsTable.getTimeSlots().get(key);
                String content = (slot != null && slot.getChosenStudent() != null)
                        ? slot.getChosenStudent().getName()
                        : "-";
                System.out.print(String.format("%-15s", content));
            }
            System.out.println();
        }
    }



}

*/
