package com.kvlt;

import com.google.common.collect.Lists;
import com.kvlt.entity.Person;

import java.util.List;

/**
 * GlobalParam
 *
 * @author KVLT
 * @date 2018-01-27.
 */
public class GlobalParam {

    public static List<String> sLs = Lists.newArrayList();
    public static List<Person> pLs = Lists.newArrayList();

    static {
        Person p1 = new Person(1L, "air", 11);
        Person p2 = new Person(3L, "bob", 18);
        Person p3 = new Person(7L, "cube", 21);
        Person p4 = new Person(2L, "davin", 15);
        Person p5 = new Person(5L, "evin", 31);

        pLs.add(p1);
        pLs.add(p2);
        pLs.add(p3);
        pLs.add(p4);
        pLs.add(p5);

        String[] strArr = new String[]{"abr", "text", "ste", "oks", "pop", "egc", "kfc", "kkk", ""};
        sLs.addAll(Lists.newArrayList(strArr));
    }

    public static List<Person> getBeanLs() {
        return pLs;
    }

    public static List<String> getStringLs() {
        return sLs;
    }

    public static void  toString(List ls) {
        ls.forEach(l -> {
            System.out.println(l);
        });
    }
}
