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
        try {
            List<String> list = new ArrayList<String>();
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                String str = base + base;
                base = str;
                list.add(str.intern());
            }
        } catch (OutOfMemoryError ex) {
            System.out.println("虽然内存溢出了，但是我并没有结束....");
//            ex.printStackTrace();

            for (int i=0;i<10;i++) {
                try {
                    System.out.println("sleep " + i);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

}
