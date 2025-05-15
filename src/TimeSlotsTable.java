import java.util.ArrayList;
import java.util.HashMap;

public class TimeSlotsTable {
    private HashMap<DayHourKey, TimeSlot> timeSlots;
    private ArrayList<TimeSlot> listOfTimeSlots;

    public TimeSlotsTable() {
        this.timeSlots = new HashMap<>();
        this.listOfTimeSlots = new ArrayList<>();
    }

    public HashMap<DayHourKey, TimeSlot> getTimeSlots() {
        return timeSlots;
    }
    public ArrayList<TimeSlot> getListTimeSlots() {
        return listOfTimeSlots;
    }

    /**
     * Adds time slots for the given day based on the specified parameters.
     *
     * @param day          the day for the time slots
     * @param beginningHour the starting hour in "HH:mm" format
     * @param endingHour    the ending hour in "HH:mm" format
     * @param lessonLength  the length of each lesson in minutes
     */
    public void createTimeSlots(Day day, String beginningHour, String endingHour, int lessonLength) {
        String currentHour = beginningHour;

        while (isBeforeOrEqual(currentHour, endingHour)) {
            String nextHour = addMinutes(currentHour, lessonLength);

            if (isBeforeOrEqual(nextHour, endingHour)) {
                // Create DayHourKey and TimeSlot object
                DayHourKey key = new DayHourKey(day, currentHour);
                TimeSlot slot = new TimeSlot(day, currentHour, nextHour);
                timeSlots.put(key, slot);
                listOfTimeSlots.add(slot);
                currentHour = nextHour;
            } else {
                break;
            }
        }
    }

    /**
     * Utility to check if the first time is before or equal to the second time.
     *
     * @param time1 the first time in "HH:mm" format
     * @param time2 the second time in "HH:mm" format
     * @return true if time1 is before or equal to time2
     */
    private boolean isBeforeOrEqual(String time1, String time2) {
        return time1.compareTo(time2) <= 0;
    }

    /**
     * Utility to check if the first time is strictly before the second time.
     *
     * @param time1 the first time in "HH:mm" format
     * @param time2 the second time in "HH:mm" format
     * @return true if time1 is strictly before time2
     */
    private boolean isBefore(String time1, String time2) {
        return time1.compareTo(time2) < 0;
    }

    /**
     * Utility to add minutes to a given time in "HH:mm" format.
     *
     * @param time   the time to add minutes to
     * @param minutes the number of minutes to add
     * @return the resulting time in "HH:mm" format
     */
    private String addMinutes(String time, int minutes) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        int totalMinutes = hour * 60 + minute + minutes;
        int newHour = (totalMinutes / 60) % 24;
        int newMinute = totalMinutes % 60;

        return String.format("%02d:%02d", newHour, newMinute);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TimeSlot slot : timeSlots.values()) {
            sb.append(slot).append("\n");
        }
        return sb.toString();
    }
}


