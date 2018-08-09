package com.kvlt.hash;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @author daishengkai
 * 2018-05-20 16:52
 */
public class HashMapTest {

    /**
     * 初始容量、加载因子
     */
    @Test
    public void test1() {
        HashMap map = new HashMap();
        map = new HashMap(18);
        map = new HashMap(18, 0.5f);
    }

    @Test
    public void test2() {
        int cap = 33;

        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;

        System.out.println(n);
        System.out.println(32 | 15);
    }

    @Test
    public void test3() {
        Object key = "1";
        int h = key.hashCode();
        String s = "ss";
        s.hashCode();
        int n_h = (h >>> 16);
        System.out.println(h ^ n_h);

        BigDecimal d = BigDecimal.ZERO;
        System.out.println(d.setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    @Test
    public void test4() {
        Multimap<String, String> map = ArrayListMultimap.create();
        map.put("", "");
        map.put(null, "");
        map.put(null, null);
        map.put("", null);

        System.out.println(map.size());
        System.out.println(map.asMap());
    }

}
