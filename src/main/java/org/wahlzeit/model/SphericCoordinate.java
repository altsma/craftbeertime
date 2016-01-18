package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Objects;

import static java.lang.Math.*;

public class SphericCoordinate extends AbstractCoordinate {
    private final double latitude;
    private final double longitude;
    private final double radius;

    private static final double EARTHRADIUS = 6371.0; // earth radius in kilometer

    private static HashMap<String, SphericCoordinate> allInstances = new HashMap<>();

    /**
     * @methodtype constructor
     */
    private SphericCoordinate(double latitude, double longitude) {
        assertIsValidLatitude(latitude);
        assertIsValidLongitude(longitude);

        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = 0.0;

        assertClassInvariants();
    }

    /**
     * @methodtype constructor
     */
    private SphericCoordinate(double latitude, double longitude, double radius) {
        assertIsValidLatitude(latitude);
        assertIsValidLongitude(longitude);

        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;

        assertClassInvariants();
    }

    /**
     * @methodtype get
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @methodtype get
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @methodtype get
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @methodtype get
     */
    public static SphericCoordinate getSphericCoordinate(double latitude, double longitude) {
        return SphericCoordinate.getSphericCoordinate(latitude, longitude, EARTHRADIUS);
    }

    /**
     * @methodtype get
     */
    public static SphericCoordinate getSphericCoordinate(double latitude, double longitude, double radius) {
        String key = latitude + ", " + longitude + ", " + radius;
        SphericCoordinate result = allInstances.get(key);
        if (result == null) {
            synchronized (allInstances) {
                result = allInstances.get(key);
                if (result == null) {
                    result = new SphericCoordinate(latitude, longitude, radius);
                    allInstances.put(key, result);
                }
            }
        }
        return result;
    }


    /**
     * @methodtype conversion
     */
    private SphericCoordinate asSphericCoordinate(Coordinate coordinate) {
        if (coordinate instanceof SphericCoordinate) {
            return (SphericCoordinate) coordinate;
        } else if (coordinate instanceof CartesianCoordinate) {
            CartesianCoordinate cartesianCoordinate = (CartesianCoordinate) coordinate;
            double radius = sqrt(pow(cartesianCoordinate.getX(), 2) + pow(cartesianCoordinate.getY(), 2) + pow(cartesianCoordinate.getZ(), 2));
            double latitude = atan2(cartesianCoordinate.getY(), cartesianCoordinate.getX());
            double longitude = acos(cartesianCoordinate.getZ() / radius);

            return new SphericCoordinate(latitude, longitude, radius);
        } else {
            throw new IllegalArgumentException("coordinate must be instance of SphericCoordinate or CartesianCoordinate!");
        }
    }

    /**
     * @methodtype get
     */
    public double getDistance(SphericCoordinate coordinate) {
        SphericCoordinate sphericCoordinate = asSphericCoordinate(coordinate);
        double dLat = toRadians(getLatitudinalDistance(sphericCoordinate));
        double dLng = toRadians(getLongitudinalDistance(sphericCoordinate));
        double a = sin(dLat / 2) * sin(dLat / 2) + cos(toRadians(getLatitude())) * cos(toRadians(sphericCoordinate.getLatitude())) * sin(dLng / 2) * sin(dLng / 2);
        double c = 2 * atan2(sqrt(a), sqrt(1 - a));
        double distance = EARTHRADIUS * c;

        assert distance >= 0.0 : "distance must be greater than or equal zero";
        return distance;
    }

    /**
     * @methodtype get
     */
    public double getLatitudinalDistance(SphericCoordinate coordinate) {
        assertIsNotNull(coordinate, "coordinate");

        SphericCoordinate sphericCoordinate = asSphericCoordinate(coordinate);
        return (double) round(abs(getLatitude() - sphericCoordinate.getLatitude()) * 1000000) / 1000000;
    }

    /**
     * @methodtype get
     */
    public double getLongitudinalDistance(SphericCoordinate coordinate) {
        assertIsNotNull(coordinate, "coordinate");

        SphericCoordinate sphericCoordinate = asSphericCoordinate(coordinate);
        return (double) round(abs(getLongitude() - sphericCoordinate.getLongitude()) * 1000000) / 1000000;
    }

    /**
     * @methodtype boolean-query
     */
    public boolean isEqual(SphericCoordinate coordinate) {
        SphericCoordinate sphericCoordinate = asSphericCoordinate(coordinate);

        return sphericCoordinate.getLatitude() == latitude && sphericCoordinate.getLongitude() == longitude && sphericCoordinate.getRadius() == radius;
    }

    /**
     * @methodtype boolean-query
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SphericCoordinate that = (SphericCoordinate) o;
        return Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude);
    }

    /**
     * @methodtype get
     */
    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    /**
     * @methodtype assertion
     */
    protected void assertIsNotNull(Coordinate coordinate, String valueName) {
        if (coordinate == null) {
            throw new IllegalArgumentException(valueName + " must not be null");
        }
    }

    /**
     * @methodtype assertion
     */
    private void assertIsValidLatitude(double latitude) {
        if (latitude < -90.0 || latitude > 90.0 || Double.isNaN(latitude)) {
            throw new IllegalArgumentException("Latitude must be a double between South and North Pole (-90.0 and 90.0)");
        }
    }

    /**
     * @methodtype assertion
     */
    private void assertIsValidLongitude(double longitude) {
        if (longitude < -180.0 || longitude > 180.0 || Double.isNaN(longitude)) {
            throw new IllegalArgumentException("Longitude must be a double between -180.0 and 180.0");
        }
    }

    /**
     * @methodtype assertion
     */
    protected void assertClassInvariants() {
        assert latitude <= 90 : "latitude must be a double between South and North Pole (-90.0 and 90.0)";
        assert latitude >= -90 : "latitude must be a double between South and North Pole (-90.0 and 90.0)";
        assert !Double.isNaN(latitude) : "latitude must be a double between South and North Pole (-90.0 and 90.0)";
        assert longitude <= 90 : "longitude must be a double between -180.0 and 180.0";
        assert longitude >= -90 : "longitude must be a double between -180.0 and 180.0";
        assert !Double.isNaN(longitude) : "longitude must be a double between -180.0 and 180.0 ";
        assert !Double.isNaN(radius) : "radius must be double value";
    }
}
