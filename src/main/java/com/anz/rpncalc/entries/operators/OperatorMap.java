package com.anz.rpncalc.entries.operators;

import java.util.Map;
import java.util.Set;

/**
 * Mapping of Operator keys to Operators
 *
 */
public class OperatorMap {

    private final Map<String, Operator> map;

    public OperatorMap(Map<String, Operator> map) {
        this.map = map;
    }

    public Operator get(String key) {
        return this.map.get(key);
    }

    public Set<String> allKeys() {
        return this.map.keySet();
    }
}
