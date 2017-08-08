package com.anz.rpncalc.entries.operators;

import com.anz.rpncalc.entries.operators.math.Add;
import com.anz.rpncalc.entries.operators.math.Subtract;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class OperatorMapTest {

    private OperatorMap operatorMap;

    @Before
    public void setupMap() {

        Map<String, Operator> map = new HashMap<>();
        map.put(new Add().getDisplayValue(), new Add());
        map.put(new Subtract().getDisplayValue(), new Subtract());
        operatorMap = new OperatorMap(map);
    }

    @Test
    public void hasMathOperators() {

        assertThat(operatorMap.get(new Add().getDisplayValue()), new IsInstanceOf(Add.class));
        assertThat(operatorMap.get(new Subtract().getDisplayValue()), new IsInstanceOf(Subtract.class));

    }

    @Test
    public void returnsNullForInvalidEntries() {
        assertNull(operatorMap.get("NON_EXISTING_OPERATOR"));
    }

}