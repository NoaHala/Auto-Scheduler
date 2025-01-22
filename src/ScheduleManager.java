/*
import java.util.HashSet;

public class ScheduleManager {

    public static void main(String[] args) {

        // Initialize time slots table and populate it with time slots
        TimeSlotsTable timeSlotsTable = createTimeSlotsTable();

        // Create Excel parser and initialize student set
        ExcelParser excelParser = new ExcelParser();
        HashSet<Student> nhs = new HashSet<>();

        // Parse Excel data to the DS of the students and the time slots
        excelParser.studentsExcelParser(nhs,timeSlotsTable.getTimeSlots(),"example1.xlsx");

        // starting the scheduling process

        // Sort the time slot list
        timeSlotsTable.sortTimeSlotsByStudentsCounter();

        // Loop through all the time slots and choose the student for them
        for (TimeSlot slot:timeSlotsTable.getListTimeSlots()) {
            scheduleStudentToSlot(slot);
        }


    }

    private static void scheduleStudentToSlot(TimeSlot slot) {
        //if there is no student available
        if (slot.getStudentsCounter() == 0){ return; };

        //if there is only one student available
        if (slot.getStudentsCounter() == 1) {
            //get the available student object
            Student curStudent = slot.getStudentsAvailable().get(0);

            //check if student is blocked by one of those:
            // 1. if he already had a lesson today
            // 2. if he got the required amount lessons this week
            if(curStudent.getIsBlocked() || curStudent.hasLessonThisDay(slot.getDay())){
                return;
            }

            //if he isn't blocked - choose him for this time slot
            chooseStudentForSlot(slot,curStudent);


            */
/*        //if he had already got the required amount lessons for this week
        //
        if (slot.get() >= Constants.BIG_ARBITRARY_NUM){ return;}*//*

        };

        //if it has more than one to choose
        //todo - choose from the sorted list




    }

    private static void chooseStudentForSlot(TimeSlot slot, Student curStudent) {
        slot.setChosenStudent(curStudent);
        curStudent.addLessonThisDay(slot.getDay());
        curStudent.increaseLessonsCounter();
        curStudent.removeAvailableSlot();
        if (curStudent.getNumOfLessonsThisWeek() == Constants.NUM_OF_WEEKLY_LESSONS){
            curStudent.blockStudent();
        }
    }

*/
/*    private static void updateStudentsAfterGettingALesson(Student curStudent) {
        curStudent.addLessonThisDay();
        curStudent.increaseLessonsCounter();
        curStudent.removeAvailableSlot();
        if (curStudent.getNumOfLessonsThisWeek() == Constants.NUM_OF_WEEKLY_LESSONS){
            curStudent.blockStudent();
        }


    }*//*


    private static TimeSlotsTable createTimeSlotsTable() {
        //todo - currently works only with 09:00
        TimeSlotsTable timeSlotsTable = new TimeSlotsTable();
        timeSlotsTable.createTimeSlots(Day.SUNDAY,Constants.DEFAULT_BEGINNING_TIME,Constants.DEFAULT_ENDING_TIME, Constants.DEFAULT_LESSON_LENGTH);
        timeSlotsTable.createTimeSlots(Day.MONDAY,Constants.DEFAULT_BEGINNING_TIME,Constants.DEFAULT_ENDING_TIME, Constants.DEFAULT_LESSON_LENGTH);
        timeSlotsTable.createTimeSlots(Day.TUESDAY,Constants.DEFAULT_BEGINNING_TIME,Constants.DEFAULT_ENDING_TIME, Constants.DEFAULT_LESSON_LENGTH);
        timeSlotsTable.createTimeSlots(Day.WEDNESDAY,Constants.DEFAULT_BEGINNING_TIME,Constants.DEFAULT_ENDING_TIME, Constants.DEFAULT_LESSON_LENGTH);
        timeSlotsTable.createTimeSlots(Day.THURSDAY,Constants.DEFAULT_BEGINNING_TIME,Constants.DEFAULT_ENDING_TIME, Constants.DEFAULT_LESSON_LENGTH);
        timeSlotsTable.createTimeSlots(Day.FRIDAY,Constants.DEFAULT_BEGINNING_TIME,Constants.DEFAULT_ENDING_TIME, Constants.DEFAULT_LESSON_LENGTH);

        return timeSlotsTable;
    }
}
*/
