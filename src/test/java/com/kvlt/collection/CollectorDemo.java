package com.kvlt.collection;

import com.google.common.collect.Lists;
import com.kvlt.reflect.introspector.IntrospectorTest;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author daishengkai
 * 2018-06-04 10:42
 */
public class CollectorDemo {

    private static List<String> demoLs = Lists.newArrayList();
    List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);

    static {
        demoLs = Arrays.asList("abort", "bus", "car", "desk", "error", "fail", "gmail", "home",
                "ignore", "jock", "king", "lemon", "man", "null", "open", "pencil", "query",
                "rack", "something", "test", "uo", "vi", "wear", "x", "year", "zoo");
    }

    @Test
    public void ttt1() {
        Stream<String> stream = demoLs.stream();
        System.out.println(stream.collect(Collectors.counting()));
        System.out.println(stream.count());

        System.out.println(stream.reduce("", String::concat));

        Integer len = stream.reduce(0,
                (sum, str) -> sum + str.length(), /* 累加器 */
                (a, b) -> a+b);

        System.out.println(len);
    }

    @Test
    public void ttt2() {
        Stream<Integer> stream = integers.stream();

        // 没有起始值时返回为Optional类型
        Optional<Integer> sumOptional = stream.reduce(Integer::sum);
        System.out.println(sumOptional.get());

        // 可以给一起起始种子值
        Integer sumReduce = stream.reduce(0, Integer::sum);
        System.out.println(sumReduce);

        //直接用sum方法
        Integer sum = stream.mapToInt(i -> i).sum();
        System.out.println(sum);
    }

    @Test
    public void testMin() {
        Stream<Integer> stream = integers.stream();

        Integer minReduce = stream.reduce(Integer.MAX_VALUE, Integer::min);
        System.out.println(minReduce);

        OptionalInt min = stream.mapToInt(i -> i).min();
        System.out.println(min.getAsInt());
    }
}
