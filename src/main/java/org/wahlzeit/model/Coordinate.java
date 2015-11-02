package org.wahlzeit.model;

import static java.lang.Math.*;
import java.util.Objects;

/**
 * A coordinate stores the latitude and longitude position of a photo.
 */
public class Coordinate {
    private double latitude;
    private double longitude;

    /**
     * @methodtype constructor
     */
    public Coordinate() {
        setLatitude(0.0);
        setLongitude(0.0);
    }

    /**
     * @methodtype constructor
     */
    public Coordinate(double latitude, double longitude) {
        setLatitude(latitude);
        setLongitude(longitude);
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
     * @methodtype set
     */
    public void setLatitude(double latitude) {
        if (latitude < -90.0 || latitude > 90.0) {
            throw new IllegalArgumentException("Latitude must be between South and North Pole (-90.0 and 90.0)");
        }
        this.latitude = latitude;
    }

    /**
     * @methodtype set
     */
    public void setLongitude(double longitude) {
        if (longitude < -180.0 || longitude > 180.0) {
            throw new IllegalArgumentException("Longitude must be between -180.0 and 180.0");
        }
        this.longitude = longitude;
    }

    /**
     * @methodtype get
     */
    public double getDistance(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("coordinate must not be null");
        }

        final double EARTHRADIUS = 6371.0; // in kilometer
        double dLat = toRadians(getLatitudinalDistance(coordinate));
        double dLng = toRadians(getLongitudinalDistance(coordinate));
        double a = sin(dLat / 2) * sin(dLat / 2) + cos(toRadians(getLatitude())) * cos(toRadians(coordinate.getLatitude())) * sin(dLng / 2) * sin(dLng / 2);
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
        return (double) round(abs(getLatitude() - coordinate.getLatitude()) * 1000000) / 1000000;
    }

    /**
     * @methodtype get
     */
    public double getLongitudinalDistance(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("coordinate must not be null");
        }
        return (double) round(abs(getLongitude() - coordinate.getLongitude()) * 1000000) / 1000000;
    }

    /**
     * @methodtype boolean-query
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
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
