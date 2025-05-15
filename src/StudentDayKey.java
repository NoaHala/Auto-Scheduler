import java.util.Objects;

public class StudentDayKey {


    /**  FIELDS  **/
    private final Student student;
    private final Day day;

    /**
     * Constructor.
     * Constructs a StudentDayKey object with a specific student  and day.
     *
     * @param student The start time for the time slot.
     * @param day The day of the week.
     */
    public StudentDayKey(Student student,Day day) {
        this.student = student;
        this.day = day;
    }

    public Student getStudent() {
        return student;
    }

    public Day getDay() {
        return day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentDayKey)) return false;
        StudentDayKey studentDayKey = (StudentDayKey) o;
        return Objects.equals(student, studentDayKey.student) && day == studentDayKey.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, day);
    }
}
