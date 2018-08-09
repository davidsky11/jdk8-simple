package com.kvlt.collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.*;

/**
 * @author daishengkai
 * 2018-06-06 19:29
 */
public class Demo {

    // 机床
    private static final ArrayList<String> container = Lists.newArrayListWithExpectedSize(30);

    private static final Map<String, List<String>> map = Maps.newHashMapWithExpectedSize(10);

    private static List<String> start = null;

    static {
        start = Arrays.asList("a", "b", "c", "d", "e", "f", "a", "b");
        List<String> s2 = Arrays.asList("g", "b", "h", "m", "e", "p");
        List<String> s3 = Arrays.asList("b", "g", "n", "m", "q", "s");
        List<String> s4 = Arrays.asList("q", "v", "r", "e", "w", "s");

        map.put("1", start);
        map.put("2", s2);
        map.put("3", s3);
        map.put("4", s4);
    }

    @Test
    public void distinct() {
        start.stream().distinct().forEach(ss -> {
            System.out.println(ss);
        });
    }

    @Test
    public void tryFirst() {
        List<String> tmp = map.values().stream()
                .reduce(new ArrayList(), (all, item) -> {
                    all.addAll(item);
                    return all;
                });
        Set<String> set = Sets.newHashSet(tmp);

        for (Map.Entry<String, List<String>> stringListEntry : map.entrySet()) {
            int i = 0;
            for (String s : tmp) {
                if (tmp.equals(s))    i++;
            }

            System.out.println(stringListEntry.getKey() + " : " + i);
        }

    }
}
