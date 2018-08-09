package com.kvlt.cpuCache;

/**
 * FalseSharingTest
 *      JAVA 与 CPU缓存 交互（伪共享）
 *
 *      缓存行填充
 *      volatile —— CPU要保证v变量在不同线程之间的读写可见性（缓存与主存之间保持数据一致性，性能消耗）
 *
 *      三种情况测试：
 *      1、volatile long v
 *      2、volatile long v + long p1,p2,...,p6
 *      3、long v (普通变量)
 * @author KVLT
 * @date 2018-08-09.
 */
public class FalseSharingTest {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Benchmark();
        }
    }

    public static void Benchmark() throws InterruptedException {
        int size = Runtime.getRuntime().availableProcessors();
        SharingLong[] shares = new SharingLong[size];
        for (int i = 0; i < size; i++) {
            shares[i] = new SharingLong();
        }

        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            threads[i] = new LightThread(shares, i);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        long start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.join();
        }

        long end = System.currentTimeMillis();
        System.out.printf("total costs %dms\n", end - start);
    }
}
