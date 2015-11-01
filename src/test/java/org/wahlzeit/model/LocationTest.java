package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorforNullInputName() throws Exception {
        new Location(null, new Coordinate(0.0, 0.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorforNullInputCoordinate() throws Exception {
        new Location("Awesome Location", null);
    }
}