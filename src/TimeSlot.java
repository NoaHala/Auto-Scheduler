import java.util.ArrayList;
import java.util.Comparator;

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
    private Student chosenStudent;


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
    public ArrayList<Student> getStudentsAvailable() {return studentsAvailable;}

    /**
     * Override toString().
     * Generates a string printing all the student's fields value.
     */
    @Override
    public String toString() {
        return "TimeSlot{" +
                "day=" + day +
                ", beginningHour=" + beginningHour +
                ", endingHour=" + endingHour +
                ", studentsCounter=" + studentsCounter +
                '}';
    }

    /**  OTHER METHODS  **/
    public void addAvailableStudent(Student newStudent){
        studentsAvailable.add(newStudent);
        studentsCounter++;
    }

    public void chooseStudent() {


    }

    public void sortStudentsByAvailability() {
        this.studentsAvailable.sort(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Integer.compare(s1.getRemainingAvailableSlotsNum(), s2.getRemainingAvailableSlotsNum());
            }
        });
    }
    public void setChosenStudent(Student chosenStudent) {
        this.chosenStudent = chosenStudent;
    }
}
