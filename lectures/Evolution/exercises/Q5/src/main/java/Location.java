public class Location {

    private static final int FLOOR_HEIGHT = 10;
    private static final int R = 6371;

    private final double latitude;
    private final double longitude;
    private final int floor;

    public Location(double latitude, double longitude, int floor) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.floor = floor;
    }

    public double distanceFrom(Location other) {
        //final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(latitude - other.latitude);
        double lonDistance = Math.toRadians(longitude - other.longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(other.latitude)) * Math.cos(Math.toRadians(latitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        // approximate elevations between 2 floors to be 10m
        double elevationDiff = (other.floor - floor) * FLOOR_HEIGHT;

        distance = Math.pow(distance, 2) + Math.pow(elevationDiff, 2);

        return Math.sqrt(distance);
    }
}
