package com.anz.rpncalc.stack;

import com.anz.rpncalc.entries.PositionedStackEntry;

/**
 * Loads individual entries to stack
 */
public interface StackLoader {

    void load (PositionedStackEntry newEntry);

}
