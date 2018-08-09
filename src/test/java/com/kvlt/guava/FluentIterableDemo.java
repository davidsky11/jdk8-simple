package com.kvlt.guava;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.kvlt.entity.Person;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * @author daishengkai
 * 2018-07-11 15:46
 */
public class FluentIterableDemo {

    private static List<Person> personList;
    private static Person person2;

    static {
        Person person1 = new Person("Wilma", 30, "F");
        person2 = new Person("Fred", 32, "M");
        Person person3 = new Person("Betty", 32, "F");
        Person person4 = new Person("Barney", 33, "M");
        personList = Lists.newArrayList(person1, person2, person3, person4);
    }

    /**
     * 使用Predicate整体过滤
     */
    @Test
    public void predicate() {
        // 过滤年龄大于等于32的person
        Iterable<Person> personsFilteredByAge =
                FluentIterable.from(personList).filter(new Predicate<Person>() {
                    @Override
                    public boolean apply(Person input) {
                        return input.getAge() > 31;
                    }
                });

        // Iterable有一个iterator方法，集合类都有一个Iterator方法
        for (Iterator<Person> it = personsFilteredByAge.iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }
        System.out.println(Iterables.contains(personsFilteredByAge, person2));
    }

    /**
     * 使用Function整体替换，将List<Person>转化为List<String>
     */
    @Test
    public void function() {
        // 将List<Person> 转化为 List<String>，数据源为personList。整体迭代。
        List<String> transformPersonList = FluentIterable.from(personList).transform(
                new Function<Person, String>() {
                    @Override
                    public String apply(Person person) {
                        // 不定参数，返回String类型
                        return Joiner.on("#").join(person.getName(), person.getSex(), person.getAge());
                    }
                }
        ).toList();
        for (int i = 0; i < transformPersonList.size(); i++) {
            System.out.println(transformPersonList.get(i));
        }
    }

}
