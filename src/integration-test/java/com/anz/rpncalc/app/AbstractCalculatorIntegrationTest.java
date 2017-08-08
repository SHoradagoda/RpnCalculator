package com.anz.rpncalc.app;

import com.anz.rpncalc.io.OutputWriter;
import com.anz.rpncalc.messages.UserMessageLoader;
import com.anz.rpncalc.stack.Stack;
import com.anz.rpncalc.stack.StackHistoryTracker;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Base Class for Integration Tests
 */
public abstract class AbstractCalculatorIntegrationTest {

    private static RpnCalculator app;
    private static Stack stack;
    private static StackHistoryTracker historyTracker;
    private static UserMessageLoader userMessageLoader;

    private static OutputWriter writer;
    private static TestOutputWriter testOutputWriter;
    @BeforeClass
    public static void resetApp() {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(
                        RpnCalculatorConfig.class);
        app = ctx.getBean(RpnCalculator.class);
        stack = ctx.getBean(Stack.class);
        historyTracker = ctx.getBean(StackHistoryTracker.class);
        userMessageLoader = ctx.getBean(UserMessageLoader.class);
        testOutputWriter = new TestOutputWriter();
        app.setOutputWriter (testOutputWriter);
    }

    @Before
    public void reset() {
        stack.clear();
        historyTracker.clear();
        testOutputWriter.clear();
        assertStackContent("");
    }


    protected void addInputToStackAndAssert(String input, String expected) {
        addInputToStack(input);
        assertStackContent(expected);
    }

    protected void assertStackContent(String expected) {
        assertEquals(expected, stack.toString().replace("stack: ", ""));
    }

    protected void addInputToStack(String input) {
        app.addUserInputToStack(input);
    }

    protected void assertOutputContains ( String expectedOutput){
        testOutputWriter.hasText(expectedOutput);
    }

    public static UserMessageLoader getUserMessageLoader() {
        return userMessageLoader;
    }
}

class TestOutputWriter implements OutputWriter {

    final List<String> outputHistory = Lists.newArrayList();

    @Override
    public void write(String text) {
        outputHistory.add(text);
    }

    boolean hasText (String expectedText) {
        return outputHistory.contains(expectedText);
    }

    void clear() {
        outputHistory.clear();
    }
}