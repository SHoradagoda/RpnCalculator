package com.anz.rpncalc.entries.operators.stack;

import com.anz.rpncalc.stack.Stack;
import com.anz.rpncalc.stack.StackHistoryTracker;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Clear stack content
 */
public class Clear implements StackOperator {

    @Autowired
    private StackHistoryTracker historyTracker;

    @Override
    public void operate(Stack stack) {
        stack.clear();
        historyTracker.updateHistory(stack);
    }

    @Override
    public String getDescription() {
        return "Clear the stack";
    }

    @Override
    public String getDisplayValue() {
        return "clear";
    }
}
