import java.util.HashMap;

/**
 * Represents a single student.
 * Contains all the critical information about the student for scheduling.
 * Doesn't contain the student's available time slots.
 */
public class Student {

    /**  FIELDS  **/
    private final String name;
    private final Vehicle vehicle;
    private final int priority;
    private int numOfLessonsThisWeek = 0;
    private int remainingAvailableSlots = 0;
    private boolean isBlocked = false;
    private final HashMap<Day, Boolean> hasLessonThisDay = new HashMap<>();


    /**  CONSTRUCTORS  **/
    public Student(String name, Vehicle vehicle, int priority) {
        this.name = name;
        this.vehicle = vehicle;
        this.priority = priority;
        for (Day day : Day.values()) {
            hasLessonThisDay.put(day, false);
        }
    }

    public Student(String name, int priority) {
        this.name = name;
        this.vehicle = new Vehicle() {};
        this.priority = priority;
        this.numOfLessonsThisWeek = 0;
        this.remainingAvailableSlots = 0;
    }

    /**  GETTERS  **/
    public String getName() {
        return name;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public int getPriority() {
        return priority;
    }
    public int getNumOfLessonsThisWeek() { return numOfLessonsThisWeek; }

    /**  OTHER METHODS  **/
    public void addAvailableSlot(){
        remainingAvailableSlots++;
    }
    public void removeAvailableSlot(){
        remainingAvailableSlots--;
    }

    public void increaseLessonsCounter(){
        numOfLessonsThisWeek++;
    }

    public void blockStudent(){
        this.remainingAvailableSlots += Constants.BIG_ARBITRARY_NUM;
        this.isBlocked = true;
    }

    /*public void blockStudent(){
        this.isBlocked = true;
    }*/

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public boolean hasLessonThisDay(Day day){
        return this.hasLessonThisDay.get(day);
    }

    public void addLessonThisDay(Day day){
        this.hasLessonThisDay.put(day, true);
    }
}
