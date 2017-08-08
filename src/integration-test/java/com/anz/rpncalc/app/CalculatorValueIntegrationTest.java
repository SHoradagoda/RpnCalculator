package com.anz.rpncalc.app;

import org.junit.Test;

public class CalculatorValueIntegrationTest extends AbstractCalculatorIntegrationTest {

    @Test
    public void inputValuesOnly() {

        addInputToStackAndAssert("1", "1");
        addInputToStackAndAssert("2", "1 2");
        addInputToStackAndAssert("-3", "1 2 -3");
        addInputToStackAndAssert("4 5", "1 2 -3 4 5");

    }

    @Test
    public void inputValuesWithNineDecimals() {
        addInputToStackAndAssert("1 1.123456789", "1 1.123456789");
    }
    @Test
    public void inputValuesWith10Decimals() {
        addInputToStackAndAssert("1 1.1234567891", "1 1.1234567891");
        addInputToStackAndAssert("1.1234567890", "1 1.1234567891 1.123456789");
    }


    @Test
    public void inputValuesWithMoreThan10Decimals() {

    addInputToStackAndAssert("1 1.123456789123", "1 1.1234567891");
        addInputToStackAndAssert("1.12345678919", "1 1.1234567891 1.1234567892");
    }
}

