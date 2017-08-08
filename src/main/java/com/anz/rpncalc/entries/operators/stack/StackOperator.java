package com.anz.rpncalc.entries.operators.stack;

import com.anz.rpncalc.entries.operators.Operator;
import com.anz.rpncalc.stack.Stack;

/**
 * Applies an operation on the entire stack
 *
 */
public interface StackOperator extends Operator {

    void operate (Stack stack);
}
