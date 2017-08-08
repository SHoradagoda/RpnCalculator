package com.anz.rpncalc.entries.operators.stack;

import com.anz.rpncalc.stack.Stack;
import com.anz.rpncalc.stack.StackHistoryTracker;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Undo last math operation in stack
 *
 */
public class Undo implements StackOperator {

    @Autowired
    private StackHistoryTracker historyTracker;

    @Override
    public void operate(Stack stack) {
        historyTracker.revertStackToPrevious(stack);
    }

    @Override
    public String getDescription() {
        return "Undo last math operation";
    }

    @Override
    public String getDisplayValue() {
        return "undo";
    }
}
