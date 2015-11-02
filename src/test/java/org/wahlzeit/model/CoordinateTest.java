package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CoordinateTest {
    private Coordinate london;
    private Coordinate berlin;
    private final double ZEROPOINTZERO = 0.0;
    private final double DELTA = 0.001;
    private final double NODELTA = ZEROPOINTZERO;
    private final double KMDELTA = 1.0; // allow 1km deviation

    @Before
    public void setUp() throws Exception {
        london = new Coordinate(51.5085300, -0.1257400);
        berlin = new Coordinate(52.5243700, 13.4105300);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorSetLatitudeMin() throws Exception {
        new Coordinate(-99.9, ZEROPOINTZERO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorSetLatitudeMax() throws Exception {
        new Coordinate(99.9, ZEROPOINTZERO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorSetLongitudeMin() throws Exception {
        new Coordinate(ZEROPOINTZERO, -999.9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorSetLongitudeMax() throws Exception {
        new Coordinate(ZEROPOINTZERO, 999.9);
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
        assertEquals(ZEROPOINTZERO, london.getDistance(london), NODELTA);
        assertEquals(932.0, london.getDistance(berlin), KMDELTA);
        assertEquals(london.getDistance(berlin), berlin.getDistance(london), NODELTA);
    }

    @Test
    public void testGetLatitudinalDistance() throws Exception {
        assertEquals(ZEROPOINTZERO, london.getLatitudinalDistance(london), DELTA);
        assertEquals(1.01584, london.getLatitudinalDistance(berlin), DELTA);
        assertEquals(london.getLatitudinalDistance(berlin), berlin.getLatitudinalDistance(london), NODELTA);
    }

    @Test
    public void testGetLongitudinalDistance() throws Exception {
        assertEquals(ZEROPOINTZERO, london.getLongitudinalDistance(london), DELTA);
        assertEquals(13.53627, london.getLongitudinalDistance(berlin), DELTA);
        assertEquals(london.getLongitudinalDistance(berlin), berlin.getLongitudinalDistance(london), NODELTA);
    }
}