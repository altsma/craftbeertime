package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SphericCoordinateTest {
    private SphericCoordinate defaultConstructor;
    private SphericCoordinate zero;
    private SphericCoordinate london;
    private SphericCoordinate berlin;
    private final double ZEROPOINTZERO = 0.0;
    private final double DELTA = 0.001;
    private final double NODELTA = ZEROPOINTZERO;
    private final double KMDELTA = 1.0; // allow 1km deviation

    @Before
    public void setUp() throws Exception {
        zero = SphericCoordinate.getSphericCoordinate(0.0, 0.0);
        london = SphericCoordinate.getSphericCoordinate(51.5085300, -0.1257400);
        berlin = SphericCoordinate.getSphericCoordinate(52.5243700, 13.4105300);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorSetLatitudeMin() throws Exception {
        SphericCoordinate.getSphericCoordinate(-99.9, ZEROPOINTZERO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorSetLatitudeMax() throws Exception {
        SphericCoordinate.getSphericCoordinate(99.9, ZEROPOINTZERO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorSetLongitudeMin() throws Exception {
        SphericCoordinate.getSphericCoordinate(ZEROPOINTZERO, -999.9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorSetLongitudeMax() throws Exception {
        SphericCoordinate.getSphericCoordinate(ZEROPOINTZERO, 999.9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDistanceForNullInput() throws Exception {
        zero.getDistance(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLatitudinalDistanceForNullInput() throws Exception {
        london.getLatitudinalDistance(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLongitudinalDistanceForNullInput() throws Exception {
        berlin.getLongitudinalDistance(null);
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

    @Test
    public void testIsEqual() throws Exception {
        assertTrue(zero.isEqual(zero));
    }
}