package com.kvlt.guava;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

/**
 * @author daishengkai
 * 2018-07-11 16:00
 */
public class SetsDemo {

    // s1 - s2
    Set<String> s1 = Sets.newHashSet("1", "2", "3", "4");
    Set<String> s2 = Sets.newHashSet("2", "3", "4", "5");

    /**
     * 集合差
     */
    @Test
    public void difference() {
        // 得到第一个集合中有而第二个集合没有的字符串
        Sets.SetView res = Sets.difference(s1, s2);
        for (Iterator<String> it = res.iterator(); it.hasNext(); ) {
            System.out.println(it.next()); // 1
        }
    }

    /**
     * 集合对称差
     */
    @Test
    public void symmetricDifference() {
        Sets.SetView res2 = Sets.symmetricDifference(s1, s2);
        for (Object it14 : res2) {
            System.out.println(it14); // 1 5
        }
    }

    /**
     * 集合交
     */
    @Test
    public void intersection() {
        // s1和s2的交集
        Sets.SetView<String> res3 = Sets.intersection(s1, s2);
        for (String it14 : res3) {
            System.out.println(it14); // 2 3 4
        }
    }

    /**
     * 集合并
     */
    @Test
    public void union() {
        // 合并s1和s2
        Sets.SetView<String> res4 = Sets.union(s1, s2);
        for (String it14 : res4) {
            System.out.println(it14); // 1 2 3 4 5
        }
    }
}
