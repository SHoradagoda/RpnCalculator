package com.anz.rpncalc.app;

import com.anz.rpncalc.entries.InvalidEntryException;
import com.anz.rpncalc.entries.PositionedStackEntry;
import com.anz.rpncalc.entries.converter.UserInputConverterImpl;
import com.anz.rpncalc.io.InputReader;
import com.anz.rpncalc.io.OutputWriter;
import com.anz.rpncalc.messages.UserMessageLoader;
import com.anz.rpncalc.stack.EmptyStackException;
import com.anz.rpncalc.stack.Stack;
import com.anz.rpncalc.stack.StackHistoryTracker;
import com.anz.rpncalc.stack.StackLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * This class launches the calculator.
 *
 */
public class RpnCalculator {

    @Autowired
    private UserMessageLoader userMessageLoader;

    @Autowired
    private UserInputConverterImpl userInputConverter;

    @Autowired
    private Stack stack;

    @Autowired
    private StackLoader stackLoader;

    @Autowired
    private OutputWriter outputWriter;

    @Autowired
    private InputReader inputReader;

    @Autowired
    private StackHistoryTracker historyTracker;

    public void launch ( ) {

        outputWriter.write( userMessageLoader.getInitMessage() );

        for( ;; ) {

            String input = inputReader.read();

            if ( containsQuitCommand (input)) {
                break;
            }
            if ( containsHelpCommand (input)) {
                outputWriter.write( userMessageLoader.getHelpMessage() );
                continue;
            }

            addUserInputToStack(input);

            outputWriter.write(stack.toString());

        }
    }

    private boolean containsHelpCommand(String input) {
        return !StringUtils.isEmpty(input) && input.contains(UserMessageLoader.HELP_COMMAND);
    }

    private boolean containsQuitCommand(String input) {
        return !StringUtils.isEmpty(input) && input.contains(UserMessageLoader.QUIT_COMMAND);
    }

    void addUserInputToStack(String input) {

        try {
            List<PositionedStackEntry> convertedInputs = userInputConverter.convert(input);
            loadConvertedInputsToStack(convertedInputs);
        } catch (InvalidEntryException e) {
            historyTracker.revertStackToLatest(stack);
            outputWriter.write(e.getMessage());
        }


    }

    private void loadConvertedInputsToStack(List<PositionedStackEntry> newEntries) {
        for (PositionedStackEntry newEntry : newEntries) {
            try {
                stackLoader.load(newEntry);
            } catch (EmptyStackException e) {
                historyTracker.revertStackToLatest(stack);
                outputWriter.write(String.format(
                        "operator %s (position: %d): insufficient parameters",
                        newEntry.getEntry().getDisplayValue(),
                        newEntry.getPosition()));
                break;
            }
        }
    }

    void setOutputWriter(OutputWriter outputWriter){
        this.outputWriter = outputWriter;
    }

}