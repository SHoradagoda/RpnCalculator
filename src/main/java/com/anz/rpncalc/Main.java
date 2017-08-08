package com.anz.rpncalc;

import com.anz.rpncalc.app.RpnCalculator;
import com.anz.rpncalc.app.RpnCalculatorConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main method to run RPN Calculator
 */
public class Main {

    public static void main(String[] args) {

        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(RpnCalculatorConfig.class);

        ctx.getBean(RpnCalculator.class).launch();

    }


}
