package com.anz.rpncalc.entries.operators.math;

import com.anz.rpncalc.entries.Value;

/**
 * Operators to add two values
 */
public class Add implements MathOperator {

    @Override
    public Value operate(Value... values) {
        return Value.valueOf(values[0].get().add(values[1].get()));
    }

    @Override
    public String getDescription() {
        return "Adds two values";
    }

    @Override
    public int getRequiredParameters() {
        return 2;
    }

    @Override
    public String getDisplayValue() {
        return "+";
    }

    @Override
    public String toString() {
        return getDisplayValue();
    }
}
