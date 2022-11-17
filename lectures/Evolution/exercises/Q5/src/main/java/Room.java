import java.util.*;

public class Room {


    private final RoomName name;
    private final Location location;
    private final Map<TimeSlot, Course> occupancies;

    public Room(RoomName name, double locationLatitude, double locationLongitude,
                int floor, Map<TimeSlot, Course> occupancies) {
        this.name = name;
        this.location = new Location(locationLatitude, locationLongitude, floor);
        this.occupancies = occupancies;
    }

    public boolean isAvailable() {
        return isAvailableAt(TimeSlot.now());
    }

    public boolean isAvailableAt(TimeSlot slot) {
        return !occupancies.containsKey(slot);
    }

    public String getName() {
        return name.name();
    }

    public Course.TYPE mostCommonCourseType() {
        Map<Course.TYPE, Integer> nbCoursesOccurrence =
                new TreeMap<>(Map.of(Course.TYPE.MATH, 0, Course.TYPE.ART, 0, Course.TYPE.ENGLISH, 0,
                        Course.TYPE.HISTORY, 0, Course.TYPE.GEOGRAPHY, 0));
        for (Course c : occupancies.values()) {
            nbCoursesOccurrence.replace(c.type(), nbCoursesOccurrence.get(c.type())+1);
        }
        Course.TYPE mostOccurrence = null;
        int lastMax = -1;
        for (Map.Entry<Course.TYPE, Integer> entry: nbCoursesOccurrence.entrySet()) {
            if (lastMax < entry.getValue()) {
                lastMax = entry.getValue();
                mostOccurrence = entry.getKey();
            }
        }
        return mostOccurrence;
    }
}
