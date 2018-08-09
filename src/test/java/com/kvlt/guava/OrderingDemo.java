package com.kvlt.guava;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import com.kvlt.entity.Person;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @author daishengkai
 * 2018-07-11 16:14
 */
public class OrderingDemo {

    Person person1 = new Person("Wilma", 30, "F");
    Person person2 = new Person("Fred", 32, "M");
    Person person3 = new Person("Betty", 32, "F");
    Person person4 = new Person("Barney", 33, "M");
    List<Person> list = Lists.newArrayList(person1, person2, person3, person4);

    // 自定义比较器，嵌入式的比较器，匿名类。注意这里有两个person参数，与Comparable的区别
    Comparator<Person> ageCmp = new Comparator<Person>() {
        // Ints是Guava提供的，递增
        @Override
        public int compare(Person o1, Person o2) {
            return Ints.compare(o1.getAge(), o2.getAge());
        }
    };

    // 按照名字排序
    Comparator<Person> nameCmp = new Comparator<Person>() {
        @Override // 两个对象，而Comparable是this和一个对象
        public int compare(Person o1, Person o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    /**
     * 逆置比较器
     * 组合多个比较器
     */
    @Test
    public void compare() {
        // 将比较器转化为Ordering，得到比较器ageCmp的相反比较器，递减
        Collections.sort(list, Ordering.from(ageCmp).reverse());
        for (Iterator<Person> iter = list.iterator(); iter.hasNext(); ) {
            System.out.println(iter.next());
        }
    }

    /**
     * 组合比较器
     */
    @Test
    public void fs() {
        // 组合两个比较器，得到第一二排序关键字
        // 年龄相同时按照名字排序
        Ordering order = Ordering.from(ageCmp).compound(nameCmp);
        Collections.sort(list, order);
        for (Iterator<Person> iter = list.iterator(); iter.hasNext(); ) {
            System.out.println(iter.next());
        }
    }

    /**
     * 直接获取最小几个和最大几个
     */
    @Test
    public void from() {
        Ordering order2 = Ordering.from(nameCmp);
        // 最小的两个，无序
        System.out.println("least 2...");
        List<Person> least = order2.leastOf(list, 2);
        for (int i = 0; i < 2; i++) {
            System.out.println(least.get(i));
        }
        // 最大的三个，无序
        System.out.println("greatest 3....");
        List<Person> great = order2.greatestOf(list, 3);
        for (int i = 0; i < 3; i++) {
            System.out.println(great.get(i));
        }

    }
}
