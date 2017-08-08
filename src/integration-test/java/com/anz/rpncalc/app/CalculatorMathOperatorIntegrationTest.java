package com.anz.rpncalc.app;

import org.junit.Test;

/**
 * Test Math Operators
 */
public class CalculatorMathOperatorIntegrationTest extends AbstractCalculatorIntegrationTest {


    @Test
    public void storedValuesBeyondTenPlaces() {
        addInputToStackAndAssert("0.00000000004", "0");
        addInputToStackAndAssert("0.00000000004 +", "0.0000000001");
    }


    @Test
    public void withSingleMathOperatorAtATime() {

        addInputToStackAndAssert("1 2", "1 2");
        addInputToStackAndAssert("+", "3");
        addInputToStackAndAssert("3 -", "0");
        addInputToStackAndAssert("12 2 /", "0 6");
        addInputToStackAndAssert("*", "0");

    }

    @Test
    public void withMultipleMathOperatorsAtATime() {
        addInputToStackAndAssert("1 2 3", "1 2 3");
        addInputToStackAndAssert("+ +", "6");
        addInputToStackAndAssert("5 - sqrt sqrt sqrt", "1");
    }

    @Test
    public void operatorExceedsLimitWithTwoParams() {
        addInputToStackAndAssert("1 2 + + 5 6", "3");
        assertOutputContains ( "operator + (position: 6): insufficient parameters" );
    }

    @Test
    public void operatorExceedsLimitWithSingleParam() {
        addInputToStackAndAssert("sqrt", "");
        assertOutputContains ( "operator sqrt (position: 1): insufficient parameters" );
    }

    @Test
    public void operatorExceedsLimitInMultipleCalls() {
        addInputToStackAndAssert("1 2 + ", "3");
        addInputToStackAndAssert("+ 5 6", "3");
        assertOutputContains ( "operator + (position: 1): insufficient parameters" );
    }



}
