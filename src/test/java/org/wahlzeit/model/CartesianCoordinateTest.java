package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CartesianCoordinateTest {
    private CartesianCoordinate zero;
    private CartesianCoordinate nineninenine;
    private final double DELTA = 0.001;
    private final double NODELTA = 0.0;

    @Before
    public void setUp() throws Exception {
        zero = new CartesianCoordinate(0.0, 0.0, 0.0);
        nineninenine = new CartesianCoordinate(9.0, 9.0, 9.0);
    }

    @Test
    public void testGetDistance() throws Exception {
        assertEquals(15.58845726812, zero.getDistance(nineninenine), DELTA);
        assertEquals(zero.getDistance(nineninenine), nineninenine.getDistance(zero), NODELTA);
    }

    @Test
    public void testIsEqual() throws Exception {
        assertTrue(zero.isEqual(zero));
    }
}