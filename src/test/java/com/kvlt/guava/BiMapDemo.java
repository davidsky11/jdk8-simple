package com.kvlt.guava;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

/**
 * @author daishengkai
 * 2018-07-11 16:10
 */
public class BiMapDemo {

    /**
     * 可以通过value获取key的HashBiMap
     * value不可以有相同的key
     */
    @Test
    public void hashBiMap() {
        BiMap<String, String> biMap = HashBiMap.create();
        // value可以作为Key，即value不可以有多个对应的值
        biMap.put("hello", "world");
        biMap.put("123", "tell");
        biMap.put("123", "none"); // 覆盖tell
        // biMap.put("abc", "world"); 失败
        // 下面是强制替换第一对
        biMap.forcePut("abc", "world");
        System.out.println(biMap.size()); // 2
        System.out.println(biMap.get("hello"));// null
        System.out.println(biMap.get("abc")); // world
        System.out.println(biMap.get("123")); // none

        // 键值对互换
        BiMap<String, String> inverseMap = biMap.inverse();
        System.out.println(inverseMap.get("world")); // abc
        System.out.println(inverseMap.get("tell")); // null
        System.out.println(inverseMap.get(null)); // null

    }
}
