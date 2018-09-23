package com.kvlt.tryCatch;

import org.junit.Test;

/**
 * TryCatchDemo
 *
 * @author KVLT
 * @date 2018-09-16.
 */
public class TryCatchDemo {

    @Test
    public void test1() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < 1000000; i++) {
            a++;
        }
        System.out.println(System.currentTimeMillis() - start + "毫秒");

        test2();
    }

    @Test
    public void test2() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < 1000000; i++) {
            try {
                a++;
                throw new Exception();
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis() - start + "毫秒");
    }
}
