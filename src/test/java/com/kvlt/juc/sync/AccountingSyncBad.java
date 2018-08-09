package com.kvlt.juc.sync;

import org.junit.Test;

/**
 * synchronized 修饰实例方法
 * @author daishengkai
 * 2018-05-18 10:56
 */
public class AccountingSyncBad implements Runnable {

    // 共享资源（临界资源）
    static int i = 0;

    /**
     * synchronized 修饰实例方法（新建一个实例，都会产生一个锁）
     *  非静态方法，每个实例都会有自有的锁
     *  非静态，访问时锁不一样不会发生互斥
     */
    synchronized void increase4Obj() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            increase4Obj();
        }
    }

    /**
     * 基于同一个实例起的线程，锁是一样的（线程安全）
     * 输出结果:  2000000
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        AccountingSyncBad instance = new AccountingSyncBad();
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

    /**
     * 两个实例对象锁不相同，如果两个线程操作数据共享，那么线程安全就有可能无法保证（线程不安全）
     * 输出结果：  1903374 ~ 1849142
     * @throws InterruptedException
     */
    @Test
    public void test2() throws InterruptedException {
        //new新实例
        Thread t1 = new Thread(new AccountingSyncBad());
        //new新实例
        Thread t2 = new Thread(new AccountingSyncBad());
        t1.start();
        t2.start();
        //join含义:当前线程A等待thread线程终止之后才能从thread.join()返回
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
