package com.anz.rpncalc.stack;

import com.anz.rpncalc.entries.PositionedStackEntry;
import com.anz.rpncalc.entries.Value;
import com.anz.rpncalc.entries.operators.math.MathOperator;
import com.anz.rpncalc.io.OutputWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StackLoaderImplTest {

    @InjectMocks
    private StackLoader stackLoader = new StackLoaderImpl();

    @Mock
    private Stack stack;

    @Mock
    private OutputWriter writer;

    @Mock
    private MathOperator mathOperator;

    @Mock
    private StackHistoryTracker historyTracker;

    private static final Value TEST_VALUE = Value.valueOf(1);
    private static final Value TEST_VALUE_2 = Value.valueOf(2);
    private static final Value RESULT_VALUE = Value.valueOf(3);

    @Before
    public void setupOperatorMap () {
        stack = mock (Stack.class);
        writer = mock (OutputWriter.class);
        mathOperator = mock(MathOperator.class);
        historyTracker = mock (StackHistoryTracker.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadValue() {
        stackLoader.load(new PositionedStackEntry(1, TEST_VALUE));
        verify(stack).push(TEST_VALUE);
        verify(historyTracker).updateHistory(stack);
    }


    @Test
    public void loadMathOperatorSuccessfully() {
        when(mathOperator.getRequiredParameters()).thenReturn(2);
        when(stack.pop()).thenReturn(TEST_VALUE,TEST_VALUE_2);
        when(mathOperator.operate(TEST_VALUE,TEST_VALUE_2)).thenReturn(RESULT_VALUE);
        stackLoader.load(new PositionedStackEntry(234, mathOperator));
        verify(stack).push(RESULT_VALUE);
        verify(historyTracker, times(1)).updateHistory(any(Stack.class));
    }


    @Test (expected = EmptyStackException.class)
    public void loadMathOperatorWithInsufficientParams() {
        when(mathOperator.getRequiredParameters()).thenReturn(2);
        when(stack.pop()).thenReturn(TEST_VALUE).thenThrow(new EmptyStackException());
        when(mathOperator.operate(TEST_VALUE,TEST_VALUE_2)).thenReturn(RESULT_VALUE);
        when(mathOperator.getDisplayValue()).thenReturn("TEST_OPERATOR");
        stackLoader.load(new PositionedStackEntry(234, mathOperator));
        fail("Should not allow loading of math operator with insufficient params");
    }

}