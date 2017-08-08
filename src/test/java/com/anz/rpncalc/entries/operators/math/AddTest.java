package com.anz.rpncalc.entries.operators.math;

import org.junit.Test;

import java.math.BigDecimal;

import static com.anz.rpncalc.entries.Value.valueOf;
import static org.junit.Assert.*;

public class AddTest {

    @Test
    public void withPositives() {
        testWith(1,2);
        testWith(Constants.LARGE_NUMBER, Constants.LARGE_NUMBER);
        testWith(1.005,2.05);
    }


    @Test
    public void withNegatives() {
        testWith(-1,-2);
        testWith(-Constants.LARGE_NUMBER,-Constants.LARGE_NUMBER);
        testWith(-1.005,-2.05);
    }

    @Test
    public void withPositivesWithNegatives() {
        testWith(1,-2);
        testWith(1,-1);
        testWith(1,-2.01);
    }



    private void testWith(double val1, double val2) {

        BigDecimal expected  = BigDecimal.valueOf(val1).add(BigDecimal.valueOf(val2));
        assertEquals(valueOf(expected), new Add().operate(valueOf(val1), valueOf(val2)));
    }

}