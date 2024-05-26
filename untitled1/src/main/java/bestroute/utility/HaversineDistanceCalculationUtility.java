package bestroute.utility;

import bestroute.dto.Location;

public class HaversineDistanceCalculationUtility {
    private static final double EARTH_RADIUS = 6371; // in km
    public static double getHaversineDistance(Location location1, Location location2) {
        double longitude1 = Math.toRadians(location1.getLongitude());
        double longitude2 = Math.toRadians(location2.getLongitude());
        double latitude1 = Math.toRadians(location1.getLatitude());
        double latitude2 = Math.toRadians(location2.getLatitude());

        double x = (longitude2 - longitude1) * Math.cos((latitude1 + latitude2) / 2);
        double y = (latitude2 - latitude1);
        return Math.sqrt(x * x + y * y) * EARTH_RADIUS;
    }
}
