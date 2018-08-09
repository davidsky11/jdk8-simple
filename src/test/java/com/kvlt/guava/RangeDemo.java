package com.kvlt.guava;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Range;
import com.kvlt.entity.Person;
import org.junit.Test;

/**
 * @author daishengkai
 * 2018-07-11 16:12
 */
public class RangeDemo {

    Person person1 = new Person("Wilma", 30, "F");

    @Test
    public void range() {
        // 闭区间
        Range<Integer> closedRange = Range.closed(30, 33);
        System.out.println(closedRange.contains(30)); // true
        System.out.println(closedRange.contains(33)); // true

        // 开区间
        Range<Integer> openRange = Range.open(30, 33);
        System.out.println(openRange.contains(30)); // false
        System.out.println(openRange.contains(33)); // false

        Function<Person, Integer> ageFunction = new Function<Person, Integer>() {
            @Override
            public Integer apply(Person person) {
                return person.getAge();
            }
        };
        // Range实现了Predicate接口，这里的第一个参数是Predicate，第二个参数是Function
        // ageFunction必须返回整数
        Predicate<Person> agePredicate = Predicates.compose(closedRange, ageFunction);
        System.out.println(agePredicate.apply(person1)); // person1.age == 30 true
    }
}
