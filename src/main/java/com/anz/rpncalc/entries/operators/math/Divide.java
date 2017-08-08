package com.anz.rpncalc.entries.operators.math;

import com.anz.rpncalc.entries.Value;

import java.math.BigDecimal;

/**
 * Operators to divide two values
 */
public class Divide implements MathOperator {

    @Override
    public Value operate(Value... values) {
        return Value.valueOf(values[1].get().divide(
                values[0].get() , 15, BigDecimal.ROUND_HALF_UP));
    }

    @Override
    public int getRequiredParameters() {
        return 2;
    }

    @Override
    public String getDescription() {
        return "Divides two numbers";
    }

    @Override
    public String getDisplayValue() {
        return "/";
    }

    @Override
    public String toString() {
        return getDisplayValue();
    }
}
