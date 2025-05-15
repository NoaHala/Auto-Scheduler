import java.util.HashSet;
import java.util.*;

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
    private HashSet<TimeSlot> availableTimeSlots;


    /**  CONSTRUCTORS  **/
    public Student(String name, Vehicle vehicle, int priority) {
        this.name = name;
        this.vehicle = vehicle;
        this.priority = priority;
        this.availableTimeSlots = new HashSet<>();
    }

    public Student(String name, int priority) {
        this.name = name;
        this.vehicle = new Vehicle() {};
        this.priority = priority;
        this.availableTimeSlots = new HashSet<>();
        //this.numOfLessonsThisWeek = 0;
        //this.remainingAvailableSlots = 0;
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
    public HashSet<TimeSlot> getAvailableTimeSlots() {
        return availableTimeSlots;
    }


    /**  OTHER METHODS  **/

    public void addAvailableTimeSlot(TimeSlot timeSlot){
        this.availableTimeSlots.add(timeSlot);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(this.getName(), student.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    }
