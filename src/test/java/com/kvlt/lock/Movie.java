package com.kvlt.lock;

import java.util.concurrent.CountDownLatch;

/**
 * Movie
 *  锁的性能优化
 *      1、缩短锁持有的时间
 *      2、减小锁的粒度
 *      3、锁分离（读写锁）
 *
 *      公平锁     synchronized、ReentrantLock
 *      非公平锁   ReentrantLock、CAS
 *      独享锁     synchronized、ReentrantLock
 *      共享锁     Semaphore
 *
 * @author KVLT
 * @date 2018-08-13.
 */
public class Movie {
    private final CountDownLatch ctl = new CountDownLatch(1);

    // 复仇者联盟3
    private Integer avengers3Tickets = 20;

    // 侏罗纪世界
    private Integer jurass2Tickets = 100;

    /**
     * synchronized   监视器锁（对象锁） 互斥的
     * @throws InterruptedException
     */
    public /*synchronized*/ void showAvengers3() throws InterruptedException {
        synchronized (avengers3Tickets) {
            System.out.println("复仇者联盟3剩余票量：" + avengers3Tickets);
            ctl.await();
        }
    }

    public /*synchronized*/ void showJurassic2() throws InterruptedException {
        synchronized (jurass2Tickets) {
            System.out.println("侏罗纪世界2剩余票量：" + jurass2Tickets);
            ctl.await();
        }
    }

    public static void main(String[] args) {
        final Movie movie = new Movie();
        new Thread(() -> {
            try {
                movie.showAvengers3();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "用户A").start();

        new Thread(() -> {
            try {
                movie.showJurassic2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "用户B").start();
    }
}
