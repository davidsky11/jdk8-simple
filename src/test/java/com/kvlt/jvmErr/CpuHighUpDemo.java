package com.kvlt.jvmErr;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author daishengkai
 * 2018-06-10 15:18
 */
public class CpuHighUpDemo {

    Map<Integer, Object> testMap = new HashMap<>();
    CountDownLatch cdl = new CountDownLatch(100);

    @Test
    public void hashMapCoo() throws Exception {

        Object val = new Object();

        for (int i=0; i< cdl.getCount(); i++) {
            Thread t = new Thread(new MyThread(i, val));
            t.start();
        }

        cdl.await();

        for (int i=0; i< 10000; i++) {
            assert testMap.get(i) != null;
        }
    }

    class MyThread implements Runnable {

        private int tmp;

        private Object val;

        MyThread(int tmp, Object val) {
            this.tmp = tmp;
            this.val = val;
        }

        @Override
        public void run() {
            for (int j = tmp * 100; j < (tmp + 1) * 100; j++) {
                testMap.put(j, val);
            }
            cdl.countDown();
        }

    }
}