package com.anz.rpncalc.app;

import org.junit.Test;

/**
 * * Test Stack Operators
 */
public class CalculatorStackOperatorIntegrationTest extends AbstractCalculatorIntegrationTest {


    @Test
    public void withOneClearRequestAtATime() {
        addInputToStackAndAssert("1 2 3", "1 2 3");
        addInputToStackAndAssert("clear", "");
        addInputToStackAndAssert("clear", "");
    }

    @Test
    public void withValuesClearRequestsCombined() {
        addInputToStackAndAssert("1 2 3", "1 2 3");
        addInputToStackAndAssert("4 clear", "");
        addInputToStackAndAssert("1 clear 2", "2");
        addInputToStackAndAssert("1 clear 2 clear", "");
    }

    @Test
    public void undoAddingValue() {
        addInputToStackAndAssert("1 undo", "");
        addInputToStackAndAssert("1 2 undo", "1");

    }

    @Test
    public void undoMultipleTimesWhenAddingValues() {
        addInputToStackAndAssert("1 2 3 undo undo", "1");
    }

    @Test
    public void clearThenUndo() {
        addInputToStackAndAssert("1 2 3 ", "1 2 3");
        addInputToStackAndAssert("clear", "");
        addInputToStackAndAssert("undo", "1 2 3");
    }



    @Test
    public void undoOperations() {
        addInputToStackAndAssert("1 3 + undo", "1 3");
        addInputToStackAndAssert("* undo", "1 3");
        addInputToStackAndAssert("3 4 - undo", "1 3 3 4");

    }

    @Test
    public void undoMultipleTimesWhenAddingValuesAndOperations() {
        addInputToStackAndAssert("1 2 sqrt sqrt undo undo", "1 2");
        addInputToStackAndAssert("undo", "1");
    }


}
