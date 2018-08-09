package com.kvlt.guava;

import com.google.common.base.Function;
import com.google.common.collect.*;
import com.kvlt.entity.Person;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author daishengkai
 * 2018-07-11 16:03
 */
public class MapsDemo {

    private static List<Person> personList;
    private static Person person2;

    static {
        Person person1 = new Person("Wilma", 30, "F");
        person2 = new Person("Fred", 32, "M");
        Person person3 = new Person("Betty", 32, "F");
        Person person4 = new Person("Barney", 33, "M");
        personList = Lists.newArrayList(person1, person2, person3, person4);
    }

    @Test
    public void uniqueIndex() {
        // 将List<Person> 转化为Map<String, Person>，其中键值对是person.name -> Person
        Map<String, Person> myMp = Maps.uniqueIndex(personList.iterator(), new Function<Person, String>() {
            // name作为person的键
            @Override
            public String apply(Person person) {
                return person.getName();
            }
        });
        for (String name : myMp.keySet()) {
            System.out.println(myMp.get(name));
        }
    }

    /**
     * 数组存储多值类ArrayListMultimap
     */
    @Test
    public void arrayListMultimap() {
        // 用ArrayList保存，一键多值，值不会被覆盖
        ArrayListMultimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("foo", "1");
        multimap.put("foo", "2");
        multimap.put("foo", "3");
        multimap.put("bar", "a");
        multimap.put("bar", "a");
        multimap.put("bar", "b");
        for (String it20 : multimap.keySet()) {
            // 返回类型List<String>
            System.out.println(it20 + " : " + multimap.get(it20));
        }
        // 返回所有ArrayList的元素个数的和
        System.out.println(multimap.size());
    }

    /**
     * HashTable存储多值类 HashMultimap
     */
    @Test
    public void HashMultimap() {
        //这里采用HashTable保存
        HashMultimap<String, String> hashMultimap = HashMultimap.create();
        hashMultimap.put("foo", "1");
        hashMultimap.put("foo", "2");
        hashMultimap.put("foo", "3");
        // 重复的键值对值保留一个
        hashMultimap.put("bar", "a");
        hashMultimap.put("bar", "a");
        hashMultimap.put("bar", "b");
        for (String it20 : hashMultimap.keySet()) {
            // 返回类型List<String>
            System.out.println(it20 + " : " + hashMultimap.get(it20));
        }
        // 5
        System.out.println(hashMultimap.size());
    }

    /**
     * 不可变集合类ImmutableListMultimap
     */
    @Test
    public void ImmutableListMultimap() {
        // 不可变的集合，都有一个Builder内部类。不可以修改和添加
        Multimap<Integer, String> map = new ImmutableListMultimap.Builder<Integer, String>().put(1, "hello")
                .putAll(2, "abc", "log", "in").putAll(3, "get", "up").build();
        System.out.println(map.get(2)); // [abc, log, in]
    }
}
