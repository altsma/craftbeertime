package org.wahlzeit.model;

import static java.lang.Math.*;

public abstract class AbstractCoordinate implements Coordinate {
    private final double EARTHRADIUS = 6371.0; // earth radius in kilometer

    /**
     * @methodtype query
     */
    @Override
    public double getDistance(Coordinate coordinate) {
        assert coordinate != null : "coordinate object must not be null";

        CartesianCoordinate thisCartesianCoordinate = asCartesianCoordinate(this);
        CartesianCoordinate cartesianCoordinate = asCartesianCoordinate(coordinate);

        double x = pow(cartesianCoordinate.getX() - thisCartesianCoordinate.getX(), 2);
        double y = pow(cartesianCoordinate.getY() - thisCartesianCoordinate.getY(), 2);
        double z = pow(cartesianCoordinate.getZ() - thisCartesianCoordinate.getZ(), 2);
        double distance = sqrt(x + y + z);

        assert distance >= 0.0 : "distance must be greater than or equal zero";
        return distance;
    }

    /**
     * @methodtype conversion
     */
    protected CartesianCoordinate asCartesianCoordinate(Coordinate coordinate) {
        if (coordinate instanceof CartesianCoordinate) {
            return (CartesianCoordinate) coordinate;
        } else if (coordinate instanceof SphericCoordinate) {
            SphericCoordinate sphericCoordinate = (SphericCoordinate) coordinate;
            double lat = toRadians(sphericCoordinate.getLatitude());
            double lng = toRadians(sphericCoordinate.getLongitude());
            double x = EARTHRADIUS * cos(lat) * cos(lng);
            double y = EARTHRADIUS * cos(lat) * sin(lng);
            double z = EARTHRADIUS * sin(lat);

            return new CartesianCoordinate(x, y, z);
        } else {
            throw new IllegalArgumentException("coordinate must be instance of SphericCoordinate or CartesianCoordinate!");
        }
    }

    /**
     * @methodtype boolean-query
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        assert coordinate != null : "coordinate object must not be null";

        return this == coordinate || this.getDistance(coordinate) < 0.001;
    }
}
