import java.time.LocalDateTime;

public record TimeSlot(String day, int startHour, int endHour) {

    public static TimeSlot now() {
        LocalDateTime now = LocalDateTime.now();
        return new TimeSlot(now.getDayOfWeek().name(), now.getHour(), now.getHour() + 1);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof TimeSlot that)) {
            return false;
        }
        return this.day.equals(that.day) &&
                this.startHour == that.startHour &&
                this.endHour == that.endHour;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + startHour;
        hash = 31 * hash + endHour;
        hash = 31 * hash + (day == null ? 0 : day.hashCode());
        return hash;
    }


}
