package com.kvlt.mode.providerConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Main
 *
 * @author KVLT
 * @date 2018-09-23.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        // 建立缓冲区
        BlockingQueue<PCData> queue = new LinkedBlockingDeque<>(10);
        Producer p1 = new Producer(queue);  // 建立生产者
        Producer p2 = new Producer(queue);
        Producer p3 = new Producer(queue);

        Consumer c1 = new Consumer(queue);  // 建立消费者
        Consumer c2 = new Consumer(queue);
        Consumer c3 = new Consumer(queue);

        ExecutorService service = Executors.newCachedThreadPool();   // 建立线程池
        service.execute(p1);
        service.execute(p2);
        service.execute(p3);

        service.execute(c1);
        service.execute(c2);
        service.execute(c3);

        Thread.sleep(10 * 1000);

        p1.stop();
        p2.stop();
        p3.stop();

        Thread.sleep(3000);

        // shutdown 阻止新来的任务提交，对已经提交了的任务不会产生任何影响。
        // 当已经提交的任务执行完后，它会将那些闲置的线程（idleWorks）进行中断，这个过程是异步的。
//        service.shutdown();

        // 阻止新来的任务提交，同时会中断当前正在运行的线程，即workers中的线程。
        // 另外它还将workQueue中的任务给移除，并将这些任务添加到列表中进行返回。
        service.shutdownNow();
    }
}
