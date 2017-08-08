package com.anz.rpncalc.entries.operators.math;

import com.anz.rpncalc.entries.Value;

/**
 * Operators to multiply two values
 */
public class Multiply implements MathOperator {

    @Override
    public Value operate(Value... values) {
        return Value.valueOf(values[0].get().multiply(values[1].get()));
    }

    @Override
    public int getRequiredParameters() {
        return 2;
    }

    @Override
    public String getDescription() {
        return "Multiplies two numbers";
    }

    @Override
    public String getDisplayValue() {
        return "*";
    }

    @Override
    public String toString() {
        return getDisplayValue();
    }
}
