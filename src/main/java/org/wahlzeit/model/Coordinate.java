package org.wahlzeit.model;


public interface Coordinate {
    /**
     * @methodtype query
     */
    public double getDistance(Coordinate coordinate);

    /**
     * @methodtype boolean-query
     */
    public boolean isEqual(Coordinate coordinate);
}
