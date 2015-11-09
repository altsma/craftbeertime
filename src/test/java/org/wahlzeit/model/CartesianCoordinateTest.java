package org.wahlzeit.model;

import com.google.appengine.repackaged.org.joda.time.IllegalInstantException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartesianCoordinateTest {
    private CartesianCoordinate defaultConstructor;
    private CartesianCoordinate zero;
    private CartesianCoordinate nineninenine;
    private final double ZEROPOINTZERO = 0.0;
    private final double DELTA = 0.001;
    private final double NODELTA = ZEROPOINTZERO;
    private final double KMDELTA = 1.0; // allow 1km deviation

    @Before
    public void setUp() throws Exception {
        defaultConstructor = new CartesianCoordinate();
        zero = new CartesianCoordinate(0.0, 0.0, 0.0);
        nineninenine = new CartesianCoordinate(9.0, 9.0, 9.0);
    }

    @Test
    public void testGetDistance() throws Exception {
        assertEquals(ZEROPOINTZERO, defaultConstructor.getDistance(zero), NODELTA);
        assertEquals(15.58845726812, zero.getDistance(nineninenine), DELTA);
        assertEquals(zero.getDistance(nineninenine), nineninenine.getDistance(zero), NODELTA);
    }

    @Test
    public void testIsEqual() throws Exception {
        assertTrue(defaultConstructor.isEqual(zero));
    }
}