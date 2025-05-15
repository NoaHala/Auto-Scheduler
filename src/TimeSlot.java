import java.util.ArrayList;

/**
 * Represents a time slot in a schedule, including the day, start and end times,
 * and the list of students available during this time slot.
 *
 * This class is used to model a specific time period on a given day,
 * keeping track of available students and providing utility methods
 * for managing them.
 */
public class TimeSlot {

    /**  FIELDS  **/
    private Day day;
    private String beginningHour;
    private int lengthInMinutes = Constants.DEFAULT_LESSON_LENGTH;
    private String endingHour;
    private int studentsCounter;
    private ArrayList<Student> studentsAvailable;
    private Student chosenStudent = null;


    /**  CONSTRUCTOR  **/
    public TimeSlot(Day day, String beginningHour, String endingHour) {
        this.day = day;
        this.beginningHour = beginningHour;
        this.endingHour = endingHour;
        this.studentsCounter = 0;
        this.studentsAvailable = new ArrayList<>();
    }

    /**  GETTERS  **/
    public Day getDay() {
        return day;
    }
    public String getBeginningHour() {
        return beginningHour;
    }
    public int getStudentsCounter() {
        return studentsCounter;
    }

    //todo - why is it array list and not set?
    public ArrayList<Student> getStudentsAvailable() {return studentsAvailable;}

    /**
     * Override toString().
     * Generates a string printing all the student's fields value.
     */
    @Override
    public String toString() {
        String name = "unknown";
        if (chosenStudent != null) {name = chosenStudent.getName();}
        return "TimeSlot{" +
                "day=" + day +
                ", beginningHour=" + beginningHour +
                ", endingHour=" + endingHour +
                ", chosen student=" + name +
                '}';
    }

    /**  OTHER METHODS  **/
    public void addAvailableStudent(Student newStudent){
        studentsAvailable.add(newStudent);
        studentsCounter++;
    }

    public void setChosenStudent(Student chosenStudent) {
        this.chosenStudent = chosenStudent;
    }

    public Student getChosenStudent() {
        return chosenStudent;
    }
}
