package com.anz.rpncalc.entries;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * A Value entry in the stack.
 */
public class Value implements StackEntry  {

    private final BigDecimal value;
    private static final String decimalFormatStr = "0.##########";

    private Value ( String strValue ) {
        this.value = new BigDecimal ( strValue ) ;
    }

    private Value ( BigDecimal value ) {
        this.value = value;
    }

    public static Value valueOf( String value ) {
        return new Value(value);}

    public static Value valueOf( BigDecimal value ) {
        return new Value(value);}

    public static Value valueOf( double value ) {
        return new Value(BigDecimal.valueOf(value));}

    public BigDecimal get() {
        return value;
    }

    @Override
    public String getDisplayValue() {
        return new DecimalFormat(decimalFormatStr).format(value.doubleValue());
    }

    @Override
    public String toString(){
        return getDisplayValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Value value1 = (Value) o;

        return value.compareTo(value1.value) == 0;

    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
