package com.anz.rpncalc.app;

import com.anz.rpncalc.entries.converter.UserInputConverterImpl;
import com.anz.rpncalc.entries.operators.Operator;
import com.anz.rpncalc.entries.operators.OperatorMap;
import com.anz.rpncalc.entries.operators.math.*;
import com.anz.rpncalc.entries.operators.stack.Clear;
import com.anz.rpncalc.entries.operators.stack.Undo;
import com.anz.rpncalc.io.ConsoleInputReader;
import com.anz.rpncalc.io.ConsoleOutputWriter;
import com.anz.rpncalc.io.InputReader;
import com.anz.rpncalc.io.OutputWriter;
import com.anz.rpncalc.messages.UserMessageLoader;
import com.anz.rpncalc.stack.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Spring Configuration for application
 */
@Configuration
@PropertySource( value={"classpath:app.properties"} )
public class RpnCalculatorConfig {

    @Bean
    public RpnCalculator reversePolishNotation() {
        return new RpnCalculator();
    }


    @Bean
    public Stack stack() {
        return new StackImpl();
    }

    @Bean
    public StackLoader stackLoader() {
        return new StackLoaderImpl();
    }

    @Bean
    public OutputWriter outputWriter() {
        return new ConsoleOutputWriter();
    }

    @Bean
    public InputReader inputReader() {
        return new ConsoleInputReader();
    }

    @Bean
    public StackHistoryTracker historyTracker() {
        return new StackHistoryTrackerImpl();
    }

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.name}")
    private String appName;

    @Value("${init.text}")
    private String initMessage;

    @Value("${help.text}")
    private String helpMessage;

    @Bean
    public UserMessageLoader userMessageLoader() {
        UserMessageLoader userMessageLoader = new UserMessageLoader();
        userMessageLoader.setAppVersion(appVersion);
        userMessageLoader.setAppName(appName);
        userMessageLoader.setInitMessage (initMessage);
        userMessageLoader.setHelpMessage (helpMessage);
        return userMessageLoader;
    }


    @Bean
    public UserInputConverterImpl userInputConverter() {
        return new UserInputConverterImpl();
    }

    @Bean
    public Clear clear(){
        return new Clear();
    }

    @Bean
    public Undo undo(){
        return new Undo();
    }

    @Bean
    public OperatorMap operatorMap() {
        Map<String, Operator> operatorMap = new LinkedHashMap<>();
        registerOperator(new Add(), operatorMap);
        registerOperator(new Subtract(), operatorMap);
        registerOperator(new Multiply(), operatorMap);
        registerOperator(new Divide(), operatorMap);
        registerOperator(new SquareRoot(), operatorMap);
        registerOperator(clear(), operatorMap);
        registerOperator(undo(), operatorMap);
        return new OperatorMap(operatorMap);
    }


    private void registerOperator(Operator operator, Map<String, Operator> operatorMap) {
        operatorMap.put(operator.getDisplayValue(), operator);
    }
}
