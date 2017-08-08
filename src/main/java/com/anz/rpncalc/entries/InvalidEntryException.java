package com.anz.rpncalc.entries;

/**
 *
 * Exception caused when attempting to add an invalid entry to the stack
 *
 */
public class InvalidEntryException extends RuntimeException {

    public InvalidEntryException (String message) {
        super(message);
    }

}
