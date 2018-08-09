package com.kvlt.demo;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * @author daishengkai
 * 2018-05-14 14:18
 */
public class BooleanDemo {

    @Test
    public void test1() {
        System.out.println(Boolean.getBoolean("true"));
        System.out.println(Boolean.parseBoolean("true"));
        System.out.println(Boolean.logicalAnd(true, false));
        System.out.println(Boolean.logicalOr(true, false));
        System.out.println(Boolean.logicalXor(false, false));
    }

    @Test
    public void test2() {
        int one_e = 1_100_00;
        List<String> list = Arrays.asList("item", "test", "try");

        Optional<String> reduced = list.stream().sorted()
                .reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);
    }
}
