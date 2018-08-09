package com.kvlt.jvmErr;

import java.util.ArrayList;
import java.util.List;

/**
 * Metaspace（元空间）
 * @author daishengkai
 * 2018-05-11 11:37
 */
public class StringOomMock {

    static String base = "string";

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }

}
