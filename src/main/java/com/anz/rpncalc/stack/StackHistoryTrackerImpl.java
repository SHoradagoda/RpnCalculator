package com.anz.rpncalc.stack;

import com.anz.rpncalc.entries.Value;
import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;

/**
 * Stores the historical states of th stack of as a list. Each historical states is represented as a of list of values.
 */
public class StackHistoryTrackerImpl implements StackHistoryTracker {

    private final LinkedList<List<Value>> history = new LinkedList<>();


    @Override
    public void updateHistory(Stack stack) {
        List<Value> stackValues = Lists.newArrayList();
        stack.getAll().stream().forEach(x -> stackValues.add(x));
        history.addLast(stackValues);
    }



    @Override
    public void revertStackToLatest(Stack stack) {
        applyLatestHistoryToStack(stack);
    }

    @Override
    public void revertStackToPrevious(Stack stack) {

        if (history.isEmpty()) {
            //Decided to gracefully return clear stack
            return;
        }
        history.removeLast();
        applyLatestHistoryToStack(stack);
    }

    private void applyLatestHistoryToStack(Stack stack) {
        stack.clear();
        if (history.isEmpty()) {
            return;
        }
        List<Value> lastValueList = history.getLast();
        lastValueList.forEach(value -> stack.push(value));
    }

    @Override
    public void clear() {
        history.clear();
    }
}
