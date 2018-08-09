package com.kvlt.juc.sync;

import org.junit.Test;

/**
 * synchronized 同步代码块
 * @author daishengkai
 * 2018-05-18 10:56
 */
public class AccountingSyncBlock implements Runnable {

    static AccountingSyncBlock instance = new AccountingSyncBlock();

    // 共享资源（临界资源）
    static int i = 0;

    @Override
    public void run() {
        // 省略其他耗时操作....

        // 使用同步代码块对变量i进行同步操作,锁对象为instance
        synchronized (instance) {
            for (int j = 0; j < 1000000; j++) {
                i++;
            }
        }

        // this,当前实例对象锁（线程不安全）
        /*synchronized (this) {
            for (int j = 0; j < 1000000; j++) {
                i++;
            }
        }*/

        // class对象锁（线程安全）
        synchronized (AccountingSyncBlock.class) {
            for (int j = 0; j < 1000000; j++) {
                i++;
            }
        }
    }

    /**
     * 基于同一个实例起的线程，锁是一样的（线程安全）
     * 输出结果:  2000000
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        // 同一实例，可以保证线程安全（锁相同）
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
//        Thread t2 = new Thread(new AccountingSyncBlock());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

}
