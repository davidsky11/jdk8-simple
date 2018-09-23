package com.kvlt.mode.providerConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Consumer
 *
 * @author KVLT
 * @date 2018-09-23.
 */
public class Consumer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    private BlockingQueue<PCData> queue;  // 缓冲区
    private static final int SLEEPTIME = 1000;

    public Consumer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    public void run() {
        logger.info("start Consumer id = {}", Thread.currentThread().getId());
        Random r = new Random();  // 随机等待时间

        try {
            while (true) {
                PCData data = queue.take();
                if (null != data) {
                    int re = data.getData() * data.getData();  // 计算平方
                    logger.info(MessageFormat.format("{0}*{1}={2}",
                            data.getData(), data.getData(), re));
                    Thread.sleep(r.nextInt(SLEEPTIME));
                }
            }
        } catch (InterruptedException e) {
            logger.error("消费者线程中断...", e);
            Thread.currentThread().interrupt();
        }
    }
}
