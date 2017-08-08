package com.anz.rpncalc.entries.operators.math;

import com.anz.rpncalc.entries.Value;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static com.anz.rpncalc.entries.Value.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SquareRootTest {

    @Test
    public void withPositives() {
        testWith(0);
        testWith(1);
        testWith(2.123);
        testWith(Constants.LARGE_NUMBER);
    }


    @Test
    public void withNegatives() {
        try {
            new SquareRoot().operate(Value.valueOf(-2));
            fail();
        } catch ( IllegalArgumentException e) {
            assertEquals(SquareRoot.NEGATIVE_NUMBER_ERROR, e.getMessage());
        }
    }

    private void testWith(double val1) {
        BigDecimal expected = BigDecimal.valueOf(Math.sqrt(val1));
        Assert.assertEquals(Value.valueOf(expected), new SquareRoot().operate(Value.valueOf(val1)));
    }

}