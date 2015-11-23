package org.wahlzeit.model;

import java.util.Objects;

import static java.lang.Math.*;

public class SphericCoordinate extends AbstractCoordinate {
    private double latitude;
    private double longitude;
    private double radius;

    private final double EARTHRADIUS = 6371.0; // earth radius in kilometer

    /**
     * @methodtype constructor
     */
    public SphericCoordinate() {
        assertClassInvariants();

        setLatitude(0.0);
        setLongitude(0.0);
        setRadius(0.0);

        assertClassInvariants();
    }

    /**
     * @methodtype constructor
     */
    public SphericCoordinate(double latitude, double longitude) {
        assertClassInvariants();

        setLatitude(latitude);
        setLongitude(longitude);
        setRadius(0.0);

        assertClassInvariants();
    }

    /**
     * @methodtype constructor
     */
    public SphericCoordinate(double latitude, double longitude, double radius) {
        assertClassInvariants();

        setLatitude(latitude);
        setLongitude(longitude);
        setRadius(radius);

        assertClassInvariants();
    }

    /**
     * @methodtype get
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @methodtype set
     */
    public void setLatitude(double latitude) {
        if (latitude < -90.0 || latitude > 90.0 || Double.isNaN(latitude)) {
            throw new IllegalArgumentException("Latitude must be a double between South and North Pole (-90.0 and 90.0)");
        }
        this.latitude = latitude;
    }

    /**
     * @methodtype get
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @methodtype set
     */
    public void setLongitude(double longitude) {
        if (longitude < -180.0 || longitude > 180.0 || Double.isNaN(longitude)) {
            throw new IllegalArgumentException("Longitude must be a double between -180.0 and 180.0");
        }
        this.longitude = longitude;
    }

    /**
     * @methodtype get
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @methodtype set
     */
    public void setRadius(double radius) {
        if (Double.isNaN(radius)) {
            throw new IllegalArgumentException("radius must be double value");
        }
        this.radius = radius;
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
    @Override
    public double getDistance(Coordinate coordinate) {
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
    public double getLatitudinalDistance(Coordinate coordinate) {
        assertIsNotNull(coordinate, "coordinate");

        SphericCoordinate sphericCoordinate = asSphericCoordinate(coordinate);
        return (double) round(abs(getLatitude() - sphericCoordinate.getLatitude()) * 1000000) / 1000000;
    }

    /**
     * @methodtype get
     */
    public double getLongitudinalDistance(Coordinate coordinate) {
        assertIsNotNull(coordinate, "coordinate");

        SphericCoordinate sphericCoordinate = asSphericCoordinate(coordinate);
        return (double) round(abs(getLongitude() - sphericCoordinate.getLongitude()) * 1000000) / 1000000;
    }

    /**
     * @methodtype boolean-query
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
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