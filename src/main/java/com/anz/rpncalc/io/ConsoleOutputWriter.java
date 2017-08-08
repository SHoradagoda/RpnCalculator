package com.anz.rpncalc.io;

/**
 * The Console output Writer is use to write application messages to the Console.
 */
public class ConsoleOutputWriter implements OutputWriter {

    @Override
    public void write(String text) {
        System.out.println (text );
    }
}
