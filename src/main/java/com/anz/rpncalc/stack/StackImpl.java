package com.anz.rpncalc.stack;

import com.anz.rpncalc.entries.Value;
import com.google.common.base.Joiner;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;

/**
 * Implementation of the Stack using the java.util.Stack
 */
public class StackImpl implements Stack {

    private static final Logger LOGGER = Logger.getLogger(StackImpl.class);

    private final java.util.Stack<Value> stack = new java.util.Stack<>();

    @Override
    public void push(Value entry) {
        stack.push(entry);
        LOGGER.debug(String.format("Added %s to stack ", entry));
    }

    @Override
    public Value pop() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        Value removedEntry = stack.pop();
        LOGGER.debug(String.format("Removing %s from stack ", removedEntry));
        return removedEntry;
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        return "stack: " + Joiner.on(" ").join(stack);
    }

    @Override
    public void clear() {
        stack.clear();
    }

    @Override
    public List<Value> getAll() {
        return Collections.unmodifiableList(stack);
    }
}
