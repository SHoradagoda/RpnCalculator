package com.anz.rpncalc.entries.operators.math;

import com.anz.rpncalc.entries.Value;

/**
 * Operators to get the square root of a value
 */
public class SquareRoot implements MathOperator {

    public static final String NEGATIVE_NUMBER_ERROR = "You cannot square root a negative number";

    @Override
    public Value operate(Value... values) {
        double value = values[0].get().doubleValue();
        if (value < 0) {
            throw new IllegalArgumentException(NEGATIVE_NUMBER_ERROR);
        }

        //Assuming that the limitations in double are insignificant for this app.
        double dblValue = Math.sqrt(value);

        return Value.valueOf(dblValue);
    }

    @Override
    public int getRequiredParameters() {
        return 1;
    }

    @Override
    public String getDescription() {
        return "Square root of number";
    }

    @Override
    public String getDisplayValue() {
        return "sqrt";
    }

    @Override
    public String toString() {
        return getDisplayValue();
    }
}
