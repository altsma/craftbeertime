package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.util.Objects;

/**
 * A coordinate stores the latitude and longitude position of a photo.
 */
public class Coordinate extends DataObject {
    private double latitude;
    private double longitude;

    public Coordinate() {
        setLatitude(0.0);
        setLongitude(0.0);
    }

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
        if (latitude < -90.0d || latitude > 90.0d) {
            throw new IllegalArgumentException("Latitude must be between South and North Pole (-90.0 and 90.0)");
        }
        this.latitude = latitude;
    }

    /**
     * @methodtype set
     */
    public void setLongitude(double longitude) {
        if (longitude < -180.0d || longitude > 180.0d) {
            throw new IllegalArgumentException("Longitude must be between -180.0 and 180.0");
        }
        this.longitude = longitude;
    }

    /**
     * @methodtype get
     */
    public Coordinate getDistance(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("coordinate must not be null");
        }
        return new Coordinate(getLatitudinalDistance(coordinate), getLongitudinalDistance(coordinate));
    }

    /**
     * @methodtype get
     */
    public double getLatitudinalDistance(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("coordinate must not be null");
        }
        return (double) Math.round(Math.abs(getLatitude() - coordinate.getLatitude()) * 1000000) / 1000000;
    }

    /**
     * @methodtype get
     */
    public double getLongitudinalDistance(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("coordinate must not be null");
        }
        return (double) Math.round(Math.abs(getLongitude() - coordinate.getLongitude()) * 1000000) / 1000000;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }
}
