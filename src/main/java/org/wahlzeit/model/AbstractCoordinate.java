package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {
    /**
     * @methodtype query
     */
    @Override
    public abstract double getDistance(Coordinate coordinate);

    /**
     * @methodtype boolean-query
     */
    @Override
    public abstract boolean isEqual(Coordinate coordinate);
}
