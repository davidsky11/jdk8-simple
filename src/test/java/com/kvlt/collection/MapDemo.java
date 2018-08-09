package com.kvlt.collection;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author daishengkai
 * 2018-06-13 10:54
 */
public class MapDemo {

    @Test
    public void testHashMap() {

    }

    @Test
    public void testConcurrentHashMap() {

    }

    @Test
    public void testTreeMap() {
        TreeMap<Integer, String> treeMap = Maps.newTreeMap();

        treeMap.put(10, "10");
        treeMap.put(83, "83");
        treeMap.put(15, "15");
        treeMap.put(72, "72");
        treeMap.put(20, "20");
        treeMap.put(60, "60");
        treeMap.put(30, "30");
        treeMap.put(50, "50");

        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        System.out.println(treeMap.get(72));
        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.lastKey());

        // 获取集合内的key小于28的key
        System.out.println(treeMap.lowerKey(28));

        // 获取集合内的key大于等于28的key
        System.out.println(treeMap.ceilingKey(28));

        // 获取集合的key从"a"到"jiaboyan"的元素
        SortedMap<Integer, String> sortedMap = treeMap.subMap(28, 59);

        // 删除元素
        System.out.println(treeMap.remove(50));

        treeMap.clear();

        // 判断方法
        System.out.println(treeMap.isEmpty());
        System.out.println(treeMap.containsKey(10));
    }

    {



    }
}


