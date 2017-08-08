package com.anz.rpncalc.entries.operators;

import com.anz.rpncalc.entries.StackEntry;

/**
 * Base class for Operator
 *
 * @see com.anz.rpncalc.entries.operators.math.MathOperator
 * @see com.anz.rpncalc.entries.operators.stack.StackOperator
 *
 */
public interface Operator extends StackEntry {

    String getDescription();

}
