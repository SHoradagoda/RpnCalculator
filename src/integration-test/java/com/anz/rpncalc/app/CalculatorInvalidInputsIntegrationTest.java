package com.anz.rpncalc.app;

import com.anz.rpncalc.entries.InvalidEntryException;
import org.junit.Test;

import static junit.framework.TestCase.fail;

/**
 * Tests with Invalid inputs
 */
public class CalculatorInvalidInputsIntegrationTest extends AbstractCalculatorIntegrationTest {

    @Test
    public void errorOnInvalidInput() {
        assertError ("BAD_VALUE", "BAD_VALUE","");
    }

    @Test
    public void insertInvalidInputsDoNotAffectExisting() {
        addInputToStack("1 2");
        assertError ("BAD_VALUE", "BAD_VALUE", "1 2");

    }

    @Test
    public void whenErrorInLineWholeLineIsIgnored() {
        addInputToStackAndAssert("1 2 3", "1 2 3");
        assertError ("4 5 BAD_VALUE", "BAD_VALUE", "1 2 3");
        assertError ("BAD_VALUE 4 5", "BAD_VALUE", "1 2 3");
    }


    private void assertError(String input, String  invalidEntry, String expectedSack) {
            addInputToStack(input);
            assertOutputContains (invalidEntry );
            assertStackContent(expectedSack);
    }

}
