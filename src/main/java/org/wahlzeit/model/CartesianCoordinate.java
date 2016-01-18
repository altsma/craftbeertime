package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Objects;

public class CartesianCoordinate extends AbstractCoordinate {
    private final double x;
    private final double y;
    private final double z;

    private static HashMap<String, CartesianCoordinate> allInstances = new HashMap<>();

    /**
     * @methodtype constructor
     */
    private CartesianCoordinate(double x, double y, double z) {
        assertIsNotNaN(x, "x");
        assertIsNotNaN(y, "y");
        assertIsNotNaN(z, "z");

        this.x = x;
        this.y = y;
        this.z = z;

        assertClassInvariants();
    }

    /**
     * @methodtype get
     */
    public double getX() {
        return x;
    }

    /**
     * @methodtype get
     */
    public double getY() {
        return y;
    }

    /**
     * @methodtype get
     */
    public double getZ() {

        return z;
    }

    /**
     * @methodtype get
     */
    public static CartesianCoordinate getCartesianCoordinate(double x, double y, double z) {
        String key = x + ", " + y + ", " + z;
        CartesianCoordinate result = allInstances.get(key);
        if (result == null) {
            synchronized (allInstances) {
                result = allInstances.get(key);
                if (result == null) {
                    result = new CartesianCoordinate(x, y, z);
                    allInstances.put(key, result);
                }
            }
        }
        return result;
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
        assert !Double.isNaN(this.getZ()) : "z must be a number";
    }
}
