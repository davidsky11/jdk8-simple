package com.kvlt.mode.providerConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Producer 生产者线程
 *
 * @author KVLT
 * @date 2018-09-23.
 */
public class Producer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    private volatile boolean isRunning = true;
    private BlockingQueue<PCData> queue;  // 内存缓冲区
    private static AtomicInteger count = new AtomicInteger();  // 总数，原子操作
    private static final int SLEEPTIME  = 1000;

    public Producer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    public void run() {
        PCData data = null;
        Random r = new Random();

        logger.info("start producer id = {}", Thread.currentThread().getId());

        try {
            while (isRunning) {
                Thread.sleep(r.nextInt(SLEEPTIME));
                data = new PCData(count.incrementAndGet());
                logger.info(data + " is put into queue");
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {  // 提交数据到缓冲区
                    logger.error("failed to put data: {}", data);
                }
            }
        } catch (InterruptedException e) {
            logger.error("生产者线程中断...", e);
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        isRunning = false;
    }

}
