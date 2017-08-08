package com.anz.rpncalc.entries.operators.math;


import com.anz.rpncalc.entries.Value;
import com.anz.rpncalc.entries.operators.Operator;

/**
 * Applies a mathematical operation on the provided set of parameters
 *
 */
public interface MathOperator extends Operator {

    Value operate (Value... values);

    int getRequiredParameters();
}
