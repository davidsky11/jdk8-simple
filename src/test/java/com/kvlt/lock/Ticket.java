package com.kvlt.lock;

import com.kvlt.lock.redis.RedisDistributedLock;

import java.util.concurrent.CountDownLatch;

/**
 * Ticket
 *  多线程消费
 *
 * @author KVLT
 * @date 2018-08-13.
 */
public class Ticket {

    private static CountDownLatch ctl = new CountDownLatch(1);
    RedisDistributedLock lock = new RedisDistributedLock();

    /* 初始库存量 */
    Integer stock = 8;

    /**
     * 会有超卖的问题
     * @param num
     */
    public void reduce(int num) {
        if ((stock - num) >= 0) {
            try {
                ctl.await();
             } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock -= num;
            System.out.println(Thread.currentThread().getName() + "成功：卖出" + String.valueOf(num) + "张，库存剩余" + stock + "张");
        } else {
            System.out.println(Thread.currentThread().getName() + "失败：库存不足" + String.valueOf(num) + "张，库存剩余" + stock + "张");
        }
    }

    /**
     * 整个方法加锁（串行执行，效率低）
     * @param num
     */
    public synchronized void reduceByLock(int num) {
        boolean flag = false;
        synchronized (stock) {
            if ((stock - num) >= 0) {
                try {
                    ctl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stock -= num;
                flag = true;
            }
        }

        if (flag) {
            System.out.println(Thread.currentThread().getName() + "成功：卖出" + String.valueOf(num) + "张，库存剩余" + stock + "张");
        } else {
            System.out.println(Thread.currentThread().getName() + "失败：库存不足" + String.valueOf(num) + "张，库存剩余" + stock + "张");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Ticket ticket = new Ticket();
        for (int i = 0; i < 12; i++) {
            new Thread(() -> ticket.reduceByLock(1), "用户" + (i + 1)).start();
        }
        Thread.sleep(1000L);
        ctl.countDown();
    }
}
