package com.kvlt.functioninterface;

import org.junit.Test;

import java.util.function.Function;

/**
 * @author daishengkai
 * 2018-06-22 17:36
 */
public class FunctionDemo {

    @Test
    public void function() {
        Function<String, Integer> strToIntFunc = Integer::parseInt;
    }
}
