package org.wahlzeit.model;

import java.util.Objects;

import static java.lang.Math.*;

public class SphericCoordinate implements Coordinate {
    private double latitude;
    private double longitude;
    private double radius;

    private final double EARTHRADIUS = 6371.0; // earth radius in kilometer

    /**
     * @methodtype constructor
     */
    public SphericCoordinate() {
        setLatitude(0.0);
        setLongitude(0.0);
        setRadius(0.0);
    }

    /**
     * @methodtype constructor
     */
    public SphericCoordinate(double latitude, double longitude) {
        setLatitude(latitude);
        setLongitude(longitude);
        setRadius(0.0);
    }

    /**
     * @methodtype constructor
     */
    public SphericCoordinate(double latitude, double longitude, double radius) {
        setLatitude(latitude);
        setLongitude(longitude);
        setRadius(radius);
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

        return EARTHRADIUS * c;
    }

    /**
     * @methodtype get
     */
    public double getLatitudinalDistance(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("coordinate must not be null");
        }
        SphericCoordinate sphericCoordinate = asSphericCoordinate(coordinate);
        return (double) round(abs(getLatitude() - sphericCoordinate.getLatitude()) * 1000000) / 1000000;
    }

    /**
     * @methodtype get
     */
    public double getLongitudinalDistance(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("coordinate must not be null");
        }
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
}
