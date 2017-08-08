package com.anz.rpncalc.entries.converter;

import com.anz.rpncalc.entries.PositionedStackEntry;

import java.util.List;

public interface InputConverter {


    /**
     * Convert the user input line to a list of PositionStackEntry value
     *
     * @param input The full user input
     * @return A list of Position stack entries which is a converted from the user input
     * @see PositionedStackEntry
     */
    List<PositionedStackEntry> convert(String input);

}
