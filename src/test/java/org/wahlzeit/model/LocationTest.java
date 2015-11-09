package org.wahlzeit.model;

import org.junit.Test;

public class LocationTest {
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorforNullInputName() throws Exception {
        new Location(null, new SphericCoordinate(0.0, 0.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorforEmptyInputName() throws Exception {
        new Location("", new SphericCoordinate(0.0, 0.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorforNullInputCoordinate() throws Exception {
        new Location("Awesome Location", null);
    }
}