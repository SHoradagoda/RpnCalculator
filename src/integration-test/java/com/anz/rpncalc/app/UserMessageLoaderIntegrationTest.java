package com.anz.rpncalc.app;

import com.anz.rpncalc.entries.operators.math.Add;
import com.anz.rpncalc.entries.operators.math.SquareRoot;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class UserMessageLoaderIntegrationTest extends AbstractCalculatorIntegrationTest {

    @Test
    public void initMessageContainsAppDetails() {
        String initMessage = getUserMessageLoader().getInitMessage();
        assertThat(initMessage, containsString("Calculator"));
    }


        @Test
    public void helpMessageContainsOperators() {
        String helpMessage = getUserMessageLoader().getHelpMessage();

        assertThat(helpMessage, containsString(new Add().getDisplayValue()));
        assertThat(helpMessage, containsString(new Add().getDescription()));

        assertThat(helpMessage, containsString(new SquareRoot().getDisplayValue()));
        assertThat(helpMessage, containsString(new SquareRoot().getDescription()));

    }
}
