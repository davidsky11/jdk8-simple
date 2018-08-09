package com.kvlt.collection;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.SortedMap;
import java.util.TreeSet;

/**
 * @author daishengkai
 * 2018-06-12 18:08
 */
public class SetDemo {

    @Test
    public void testHashSet() {

    }

    @Test
    public void testTreeSet() {
        TreeSet<String> stringTreeSet = Sets.newTreeSet();
        stringTreeSet.add("first");
        stringTreeSet.add("second");
        stringTreeSet.add("third");
        stringTreeSet.add("fourth");


    }
}
