package com.kvlt.functioninterface;

import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;

/**
 * @author daishengkai
 * 2018-04-16 16:51
 */
public class CollectorDemo {

    @Test
    public void tets() {
        List<String> ls = Lists.newArrayList();
        ls.add("test");
        ls.add("try");
        List tmp = ls.stream().collect(Collector.of(ArrayList::new, (lst, s) -> lst.add(s + " --> "), null, Collector.Characteristics.IDENTITY_FINISH));
        for (Object o : tmp) {
            System.out.println(o);
        }
    }

    @Test
    public void ste() {
        List<String> names = Arrays.asList("shekhar", "rahul", "shekhar");
        Multiset<String> set = names.stream().collect(new MultisetCollector<>());

        set.forEach(str -> System.out.println(str + ":" + set.count(str)));
    }
}
