import java.util.*;

/**
 * Represents a unique key for identifying a specific time slot within a day.
 * This class is used as a key in the time slots HashMap.
 */
class DayHourKey {

    /**  FIELDS  **/
    private final Day day;
    private final String beginningHour;

    /**
     * Constructor.
     * Constructs a DayHourKey object with a specific day and start time.
     *
     * @param day The day of the week.
     * @param beginningHour The start time for the time slot.
     */
    public DayHourKey(Day day, String beginningHour) {
        this.day = day;
        this.beginningHour = beginningHour;
    }

    /**
     * Override equals().
     * Checks if two DayHourKey objects are equal based on day and start time.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayHourKey that = (DayHourKey) o;
        return Objects.equals(beginningHour, that.beginningHour) && day == that.day;
    }

    /**
     * Override hashCode().
     * Generates a hash code based on day and start time.
     */
    @Override
    public int hashCode() {
        return Objects.hash(day, beginningHour);
    }

    /**
     * Override toString().
     * Generates a string printing value for DayHourKey object.
     */
    @Override
    public String toString() {
        return "DayHourKey{" +
                "day=" + day +
                ", beginningHour=" + beginningHour +
                '}';
    }
}