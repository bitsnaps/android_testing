package com.corposense.testingandroid;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BodyMassIndexTest {

    public static final Double WEIGHT_VALUE = 55.0;
    public static final Double HEIGHT_VALUE = 1.5;

    @Test
    public void testBodyMassIndexCalculation() {
        BodyMassIndex bodyMassIndex = new BodyMassIndex(WEIGHT_VALUE, HEIGHT_VALUE);
        assertEquals(bodyMassIndex.calculate(), 24.44, 0.01);
    }

}