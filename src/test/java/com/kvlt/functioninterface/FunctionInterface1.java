package com.kvlt.functioninterface;

import org.junit.Test;

/**
 * FunctionInterface1
 *
 * @author KVLT
 * @date 2018-01-27.
 */
public class FunctionInterface1 {

    /**
     * FunctionalInterface
     * 1、使用于接口上的注解
     * 2、只能定义一个接口函数
     * 3、但是可以定义多个default关键字的函数
     * 4、可以定义多个static关键字的函数
     *
     * ==> 函数式接口，可以直接接受一个函数的赋值，从而减少代码
     */
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
//        Converter<String, Integer> integerConverter = from -> Integer.parseInt(from);
        Converter<String, Integer> integerConverter = Integer::parseInt;

        System.out.println(integerConverter.convert("123"));
    }
}
