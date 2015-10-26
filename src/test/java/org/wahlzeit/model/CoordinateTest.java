package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

public class CoordinateTest {
    private final Coordinate LONDON = new Coordinate(51.5085300, -0.1257400);
    private final Coordinate BERLIN = new Coordinate(52.5243700, 13.4105300);
    private final double DELTA = 0.001d;
    private final double NODELTA = 0.0d;

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
        Assert.assertEquals(new Coordinate(0.0, 0.0), LONDON.getDistance(LONDON));
        Assert.assertEquals(new Coordinate(1.01584, 13.53627), LONDON.getDistance(BERLIN));
        Assert.assertEquals(LONDON.getDistance(BERLIN), BERLIN.getDistance(LONDON));
    }

    @Test
    public void testGetLatitudinalDistance() throws Exception {
        Assert.assertEquals(0.0d, LONDON.getLatitudinalDistance(LONDON), DELTA);
        Assert.assertEquals(1.01584d, LONDON.getLatitudinalDistance(BERLIN), DELTA);
        Assert.assertEquals(LONDON.getLatitudinalDistance(BERLIN), BERLIN.getLatitudinalDistance(LONDON), NODELTA);
    }

    @Test
    public void testGetLongitudinalDistance() throws Exception {
        Assert.assertEquals(0.0d, LONDON.getLongitudinalDistance(LONDON), DELTA);
        Assert.assertEquals(13.53627d, LONDON.getLongitudinalDistance(BERLIN), DELTA);
        Assert.assertEquals(LONDON.getLongitudinalDistance(BERLIN), BERLIN.getLongitudinalDistance(LONDON), NODELTA);
    }
}