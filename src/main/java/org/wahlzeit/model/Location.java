package org.wahlzeit.model;

public class Location {
    private String name;
    public Coordinate coordinate;

    /**
     * @methodtype constructor
     */
    public Location(String name, Coordinate coordinate) {
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null");
        }

        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate must not be null");
        }

        this.name = name;
        this.coordinate = coordinate;
    }

    /**
     * @methodtype get
     */
    public String getName() {
        return name;
    }

    /**
     * @methodtype get
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }
}
