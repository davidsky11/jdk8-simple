package com.kvlt.lambda;

import org.junit.Test;

/**
 * FunctionInterface1
 *
 * @author KVLT
 * @date 2018-01-27.
 */
public class FunctionInterface1 {

    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);

        default String fun1() {
            System.out.println(" ==== ");
            return "ok";
        }
    }

    @Test
    public void simple1() {
        Converter<String, Integer> integerConverter = from -> Integer.parseInt(from);

        System.out.println(integerConverter.convert("123"));
    }
}
