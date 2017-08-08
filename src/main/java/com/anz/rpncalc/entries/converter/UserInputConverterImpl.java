package com.anz.rpncalc.entries.converter;

import com.anz.rpncalc.entries.InvalidEntryException;
import com.anz.rpncalc.entries.PositionedStackEntry;
import com.anz.rpncalc.entries.StackEntry;
import com.anz.rpncalc.entries.Value;
import com.anz.rpncalc.entries.operators.Operator;
import com.anz.rpncalc.entries.operators.OperatorMap;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * This class is used to convert a user entry string into a list of PositionStackEntry
 */
public class UserInputConverterImpl implements InputConverter {

    @Autowired
    private OperatorMap operatorMap;

    @Override
    public List<PositionedStackEntry> convert(String input) {

        if (StringUtils.isEmpty(input)) {
            return Lists.newArrayList();
        }
        List<String> stringEntries = splitString(input);
        List<PositionedStackEntry> entries = convertStringEntriesToPositionStackEntries(input, stringEntries);
        return entries;
    }

    private List<String> splitString(String input) {
        return Splitter.on(" ").omitEmptyStrings().trimResults().splitToList(input);
    }

    private List<PositionedStackEntry> convertStringEntriesToPositionStackEntries(String input,
                                                                                  List<String> stringEntries) {
        List<PositionedStackEntry> entries = Lists.newArrayList();

        // The processingInput string is used to keep the remainder of the input after
        // sequentially removing stringEntries
        String processingInput = input;

        // This is used to track the position in the input of the next entry
        int currentPosition = 1;

        for (String nextStringEntry : stringEntries) {

            int startIndexOfNextString = processingInput.indexOf(nextStringEntry);
            currentPosition += startIndexOfNextString;

            entries.add(new PositionedStackEntry(currentPosition, convertToEntry(nextStringEntry)));

            int nextStringLength = nextStringEntry.length();
            processingInput = processingInput.substring(startIndexOfNextString + nextStringLength);
            currentPosition += nextStringLength;

        }
        return entries;
    }


    private StackEntry convertToEntry(String input) {
        try {
            Operator operator = operatorMap.get(input);
            return (operator != null) ? operator : Value.valueOf(input);
        } catch (NumberFormatException e) {
            throw new InvalidEntryException(String.format("Cannot process argument '%s' ", input));
        }
    }

}
