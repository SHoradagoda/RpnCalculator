package com.anz.rpncalc.stack;

import com.anz.rpncalc.entries.Value;

import java.util.List;

/**
 *
 * Container to store stack entries and perform basis operations on stack
 *
 */
public interface Stack {

    void push (Value entry );

    Value pop () ;

    boolean isEmpty();

    void clear();

    List<Value> getAll();
}
