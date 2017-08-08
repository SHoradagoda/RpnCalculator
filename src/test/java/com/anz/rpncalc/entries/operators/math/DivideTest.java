package com.anz.rpncalc.entries.operators.math;

import com.anz.rpncalc.entries.Value;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static com.anz.rpncalc.entries.Value.valueOf;
import static org.junit.Assert.assertEquals;

public class DivideTest {


    @Test
    public void withPositives() {
        testWith(1,2);
        testWith(Constants.LARGE_NUMBER, Constants.LARGE_NUMBER);
        testWith(Constants.LARGE_NUMBER, Constants.LARGE_NUMBER*0.5);
        testWith(1.002,2.001);
    }


    @Test
    public void withNegatives() {
        testWith(-1,-2);
        testWith(-Constants.LARGE_NUMBER,-Constants.LARGE_NUMBER);
        testWith(-Constants.LARGE_NUMBER,-Constants.LARGE_NUMBER*0.5);
        testWith(-1.002,-2.001);
    }

    @Test
    public void withPositivesWithNegatives() {
        testWith(1,-2);
        testWith(1,-1);
        testWith(1,-2.01);
    }


    private void testWith(double val1, double val2) {
        BigDecimal expected = BigDecimal.valueOf(val2).divide(
                BigDecimal.valueOf(val1),
                15, BigDecimal.ROUND_HALF_UP);
        Assert.assertEquals(Value.valueOf(expected), new Divide().operate(Value.valueOf(val1), Value.valueOf(val2)));
    }

}
