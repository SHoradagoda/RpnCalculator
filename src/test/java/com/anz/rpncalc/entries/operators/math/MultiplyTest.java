package com.anz.rpncalc.entries.operators.math;

import org.junit.Test;

import java.math.BigDecimal;

import static com.anz.rpncalc.entries.Value.valueOf;
import static com.anz.rpncalc.entries.operators.math.Constants.LARGE_NUMBER;
import static org.junit.Assert.assertEquals;

public class MultiplyTest {

    @Test
    public void withPositives() {
        testWith(1,2);
        testWith(LARGE_NUMBER,LARGE_NUMBER);
        testWith(LARGE_NUMBER,LARGE_NUMBER*0.5);
        testWith(1.005,2.05);
    }


    @Test
    public void withNegatives() {
        testWith(-1,-2);
        testWith(-LARGE_NUMBER,-LARGE_NUMBER);
        testWith(-1.005,-2.05);
    }

    @Test
    public void withPositivesWithNegatives() {
        testWith(2,-3);
        testWith(1.5,-2.01);
    }

    private void testWith(double val1, double val2) {
        BigDecimal expected = BigDecimal.valueOf(val1).multiply(BigDecimal.valueOf(val2));
        assertEquals(valueOf(expected), new Multiply().operate(valueOf(val1), valueOf(val2)));
    }

}
