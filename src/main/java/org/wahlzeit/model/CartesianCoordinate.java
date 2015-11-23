package org.wahlzeit.model;

import java.util.Objects;

import static java.lang.Math.*;

public class CartesianCoordinate extends AbstractCoordinate {
    private double x;
    private double y;
    private double z;

    private final double EARTHRADIUS = 6371.0; // earth radius in kilometer

    /**
     * @methodtype constructor
     */
    public CartesianCoordinate() {
        assertClassInvariants();

        setX(0.0);
        setY(0.0);
        setZ(0.0);

        assertClassInvariants();
    }

    /**
     * @methodtype constructor
     */
    public CartesianCoordinate(double x, double y, double z) {
        assertClassInvariants();

        setX(x);
        setY(y);
        setZ(z);

        assertClassInvariants();
    }

    /**
     * @methodtype get
     */
    public double getX() {
        return x;
    }

    /**
     * @methodtype set
     */
    public void setX(double x) {
        assertIsNotNaN(x, "x");
        this.x = x;
    }

    /**
     * @methodtype get
     */
    public double getY() {
        return y;
    }

    /**
     * @methodtype set
     */
    public void setY(double y) {
        assertIsNotNaN(y, "y");
        this.y = y;
    }

    /**
     * @methodtype get
     */
    public double getZ() {

        return z;
    }

    /**
     * @methodtype set
     */
    public void setZ(double z) {
        assertIsNotNaN(z, "z");
        this.z = z;
    }

    /**
     * @methodtype conversion
     */
    private CartesianCoordinate asCartesianCoordinate(Coordinate coordinate) {
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
     * @methodtype get
     */
    @Override
    public double getDistance(Coordinate coordinate) {
        CartesianCoordinate cartesianCoordinate = asCartesianCoordinate(coordinate);

        double x = pow(cartesianCoordinate.getX() - getX(), 2);
        double y = pow(cartesianCoordinate.getY() - getY(), 2);
        double z = pow(cartesianCoordinate.getZ() - getZ(), 2);
        double distance = sqrt(x + y + z);

        assert distance >= 0.0 : "distance must be greater than or equal zero";
        return distance;
    }

    /**
     * @methodtype boolean-query
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        CartesianCoordinate cartesianCoordinate = asCartesianCoordinate(coordinate);

        return cartesianCoordinate.getX() == x && cartesianCoordinate.getY() == y && cartesianCoordinate.getZ() == z;
    }

    /**
     * @methodtype boolean-query
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartesianCoordinate that = (CartesianCoordinate) o;
        return Objects.equals(x, that.x) &&
                Objects.equals(y, that.y) &&
                Objects.equals(z, that.z);
    }

    /**
     * @methodtype get
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    /**
     * @methodtype assertion
     */
    protected void assertIsNotNaN(double value, String valueName) {
        if (Double.isNaN(value)) {
            throw new IllegalArgumentException(valueName + " must be a double value!");
        }
    }

    /**
     * @methodtype assertion
     */
    protected void assertClassInvariants() {
        assert !Double.isNaN(this.getX()) : "x must be a number";
        assert !Double.isNaN(this.getY()) : "y must be a number";
        assert !Double.isNaN(this.getY()) : "z must be a number";
    }
}
