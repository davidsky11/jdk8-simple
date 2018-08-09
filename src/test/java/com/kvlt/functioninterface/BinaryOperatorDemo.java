package com.kvlt.functioninterface;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.BinaryOperator;

/**
 * @author daishengkai
 * 2018-04-16 16:49
 */
public class BinaryOperatorDemo {

    @Test
    public void ste() {
        BinaryOperator<Integer> add = (n1, n2) -> n1 + n2;
        //apply方法用于接收参数，并返回BinaryOperator中的Integer类型
        System.out.println(add.apply(3, 4));
    }

    @Test
    public void ssst() {
        BinaryOperator<Integer> bi = BinaryOperator.minBy(Comparator.naturalOrder());
        System.out.println(bi.apply(2, 3));
    }
}
