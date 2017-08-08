package com.anz.rpncalc.entries.operators.math;

import com.anz.rpncalc.entries.Value;

/**
 * Operators to subtract two values
 */
public class Subtract implements MathOperator {

    @Override
    public Value operate(Value... values) {
        return Value.valueOf( values[1].get().subtract(values[0].get()) );
    }

    @Override
    public int getRequiredParameters() {
        return 2;
    }

    @Override
    public String getDescription() {
        return "Subtracts two values";
    }

    @Override
    public String getDisplayValue() {
        return "-";
    }

    @Override
    public String toString() {
        return getDisplayValue();
    }
}
