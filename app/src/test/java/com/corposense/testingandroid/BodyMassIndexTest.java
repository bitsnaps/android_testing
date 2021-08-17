package com.corposense.testingandroid;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BodyMassIndexTest {

    @Test
    public void testBodyMassIndexCalculation() {
        double weight = 55.0;
        double height = 1.5;
        BodyMassIndex bodyMassIndex = new BodyMassIndex(weight, height);
        assertEquals(bodyMassIndex.calculate(), 24.44, 0.01);
    }

}