package org.wahlzeit.model;

import static org.junit.Assert.*;
import org.junit.Test;

public class CoordinateTest {
    private final Coordinate LONDON = new Coordinate(51.5085300, -0.1257400);
    private final Coordinate BERLIN = new Coordinate(52.5243700, 13.4105300);
    private final double DELTA = 0.001;
    private final double NODELTA = 0.0;
    private final double KMDELTA = 1.0; // allow 1km deviation

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorSetLatitudeMin() throws Exception {
        new Coordinate(-99.9, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorSetLatitudeMax() throws Exception {
        new Coordinate(99.9, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorSetLongitudeMin() throws Exception {
        new Coordinate(0.0, -999.9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorSetLongitudeMax() throws Exception {
        new Coordinate(0.0, 999.9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDistanceForNullInput() throws Exception {
        new Coordinate().getDistance(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLatitudinalDistanceForNullInput() throws Exception {
        new Coordinate().getLatitudinalDistance(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLongitudinalDistanceForNullInput() throws Exception {
        new Coordinate().getLongitudinalDistance(null);
    }

    @Test
    public void testGetDistance() throws Exception {
        assertEquals(0.0, LONDON.getDistance(LONDON), NODELTA);
        assertEquals(932.0, LONDON.getDistance(BERLIN), KMDELTA);
        assertEquals(LONDON.getDistance(BERLIN), BERLIN.getDistance(LONDON), NODELTA);
    }

    @Test
    public void testGetLatitudinalDistance() throws Exception {
        assertEquals(0.0, LONDON.getLatitudinalDistance(LONDON), DELTA);
        assertEquals(1.01584, LONDON.getLatitudinalDistance(BERLIN), DELTA);
        assertEquals(LONDON.getLatitudinalDistance(BERLIN), BERLIN.getLatitudinalDistance(LONDON), NODELTA);
    }

    @Test
    public void testGetLongitudinalDistance() throws Exception {
        assertEquals(0.0, LONDON.getLongitudinalDistance(LONDON), DELTA);
        assertEquals(13.53627, LONDON.getLongitudinalDistance(BERLIN), DELTA);
        assertEquals(LONDON.getLongitudinalDistance(BERLIN), BERLIN.getLongitudinalDistance(LONDON), NODELTA);
    }
}