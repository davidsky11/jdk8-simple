package com.kvlt.functioninterface;

import com.google.common.collect.Maps;
import com.kvlt.entity.Person;
import org.junit.Test;

import java.util.HashMap;
import java.util.function.*;

/**
 * FunctionInterfaceBuiltin
 *
 * @author KVLT
 * @date 2018-01-27.
 */
public class FunctionInterfaceBuiltin {

    // 断言
    @Test
    public void simple1() {
        Predicate<String> predicate = s -> s != null && s.length() > 1;
        Predicate<String> predicate2 = s -> s != null && s.length() < 5;

        System.out.println(predicate.test("1"));
        System.out.println(predicate.test("123"));
        System.out.println(predicate.negate().test("1"));  // 结果取反
        System.out.println(predicate.and(predicate2).test("123"));  // 断言 结合 and
        System.out.println(predicate.or(predicate2).test(""));  // 断言 结合 or
    }

    // 函数
    @Test
    public void function() {
        Function<String, Integer> toInteger = Integer::parseInt;

        System.out.println(toInteger.apply("123"));

        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        System.out.println(backToString.apply("123"));

        // 生产
        Supplier<Person> supplier = Person::new;
        System.out.println(supplier.get());

        // 消费 不返回
        Consumer<Person> consumer = p -> System.out.println("Hello, " + p.getName());
        consumer.accept(new Person(1L, "tet", 12));

        BiConsumer<String, Integer> printKeyAndValue = (key, value) -> System.out.println(key + "\t" + value);
        printKeyAndValue.accept("one", 1);

        HashMap map = Maps.newHashMap();
        map.put("you", 12);
        map.put("me", 11);
        map.forEach(printKeyAndValue);

        System.out.println(map.getOrDefault("three", 3));
    }

    @Test
    public void runnable() {
        Runnable r1 = () -> System.out.println("hello");
        r1.run();
    }

    @Test
    public void callable() {

    }
}
