package com.anz.rpncalc.stack;

/**
 * Used to track the historical states of the stack
 */
public interface StackHistoryTracker {

    void updateHistory(Stack stack);

    void revertStackToPrevious(Stack stack);

    void revertStackToLatest(Stack stack);

    void clear();
}
