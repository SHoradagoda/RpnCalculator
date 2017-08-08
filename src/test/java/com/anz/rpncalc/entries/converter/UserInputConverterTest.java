package com.anz.rpncalc.entries.converter;

import com.anz.rpncalc.entries.InvalidEntryException;
import com.anz.rpncalc.entries.PositionedStackEntry;
import com.anz.rpncalc.entries.Value;
import com.anz.rpncalc.entries.operators.math.Add;
import com.anz.rpncalc.entries.operators.math.MathOperator;
import com.anz.rpncalc.entries.operators.OperatorMap;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserInputConverterTest {

    @InjectMocks
    private InputConverter userInputConverter = new UserInputConverterImpl();

    @Mock
    private OperatorMap operatorMap;

    private static final MathOperator TEST_OPERATOR = new Add();

    @Before
    public void setupOperatorMap() {
        operatorMap = mock(OperatorMap.class);
        when(operatorMap.get(anyString())).thenThrow(new RuntimeException("X"));
        MockitoAnnotations.initMocks(this);
        when (operatorMap.get("+")).thenReturn(TEST_OPERATOR);
    }

    @Test
    public void withNotEntries() {
        assertThat (userInputConverter.convert(null), is(empty()));
        assertThat (userInputConverter.convert(""), is(empty()));
    }

    @Test
    public void withSingleValueEntry () {
        assertThat (userInputConverter.convert("1"),
                Matchers.contains(posValue(1, "1")));
    }


    @Test
    public void withTrailingSpace () {
        assertThat (userInputConverter.convert("1 "),
                Matchers.contains(posValue(1, "1")));
        assertThat (userInputConverter.convert("1  "),
                Matchers.contains(posValue(1, "1")));
        assertThat (userInputConverter.convert("1 2 "),
                Matchers.contains(posValue(1, "1"), posValue(3,"2")));
    }


    @Test
    public void withMultipleValues () {

        assertThat (userInputConverter.convert("1 2"),
                contains(posValue(1,"1"), posValue(3, "2")));

        assertThat (userInputConverter.convert("1 25 -1"),
                contains(posValue(1, "1"), posValue(3, "25"), posValue(6, "-1")));

    }

    @Test
    public void withMultipleSpacesInBetween () {

        assertThat (userInputConverter.convert("1   25"),
                contains(posValue(1,"1"), posValue(5, "25")));

        assertThat (userInputConverter.convert("1   25   -1"),
                contains(posValue(1, "1"), posValue(5, "25"), posValue(10, "-1")));

    }

    @Test
    public void withMultipleValuesAndOperator () {

        String valuesAndOperatorInput = String.format("1 25 %s 4", TEST_OPERATOR.getDisplayValue());
        assertThat (userInputConverter.convert(valuesAndOperatorInput),
                contains( posValue (1, "1"),
                        posValue(3, "25"),
                        new PositionedStackEntry(6, TEST_OPERATOR),
                        posValue(8, "4")));


    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Test
    public void withInvalidValues () {

        expectErrorWith("invalid", "invalid");
        expectErrorWith("1 invalid 2", "invalid");
        expectErrorWith("1 2E10", "2E10");

    }

    private PositionedStackEntry posValue (int position, String valueString) {
        return new PositionedStackEntry(position, Value.valueOf(valueString));
    }
    
    private void expectErrorWith(String input, String expectedMessageContains) {
        exception.expect(InvalidEntryException.class);
        exception.expectMessage(expectedMessageContains);
        userInputConverter.convert(input);
    }


}