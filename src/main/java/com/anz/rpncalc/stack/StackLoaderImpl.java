package com.anz.rpncalc.stack;

import com.anz.rpncalc.entries.PositionedStackEntry;
import com.anz.rpncalc.entries.StackEntry;
import com.anz.rpncalc.entries.Value;
import com.anz.rpncalc.entries.operators.math.MathOperator;
import com.anz.rpncalc.entries.operators.stack.StackOperator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.IntStream;

/**
 * Implementation of loading entries to stack
 */
public class StackLoaderImpl implements StackLoader {

    @Autowired
    private Stack stack;

    @Autowired
    private StackHistoryTracker historyTracker;


    @Override
    public void load(PositionedStackEntry newEntry) {

        StackEntry newStackEntry = newEntry.getEntry();

        if (newStackEntry instanceof Value) {
            addValueToStackAndUpdateHistory((Value) newStackEntry);
        } else if (newStackEntry instanceof MathOperator) {
            applyMathOperationOnStack((MathOperator) newStackEntry);
        } else if (newStackEntry instanceof StackOperator) {
            ((StackOperator) newStackEntry).operate(stack);
        } else {
            throw new IllegalArgumentException(
                    String.format("Cannot load %s to Stack. Unknown Stack Entry Type.",
                            newStackEntry.getDisplayValue()));
        }

    }

    private void addValueToStackAndUpdateHistory(Value result) {
        stack.push(result);
        historyTracker.updateHistory(stack);


    }

    private void applyMathOperationOnStack(MathOperator newStackEntry) {
        int params = newStackEntry.getRequiredParameters();
        Value[] values = new Value[params];
        IntStream.range(0, params).forEach(i -> values[i] = stack.pop());
        Value result = newStackEntry.operate(values);
        addValueToStackAndUpdateHistory(result);
    }

}
