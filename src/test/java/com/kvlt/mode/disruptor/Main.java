package com.kvlt.mode.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Main
 *
 * @author KVLT
 * @date 2018-09-23.
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        PCDataFactory factory = new PCDataFactory();

        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        // Specify the size of the ring buffer, must be power of 2
        int bufferSize = 1024;

        //策略：
        // 1、BlockingWaitStrategy  和使用BlockingQueue非常类似，都是使用锁和条件(Condition)进行数据的监控和线程的唤醒（高并发下性能表型差）
        // 2、SleepingWaitStrategy  对于数据处理可能产生比较高的平均延时（适合对延迟要求不是特别高的场合，异步日志），好处是对生产者线程的影响最小
        // 3、YieldingWaitStrategy  低延时场合
        // 4、BusySpinWaitStrategy  最疯狂的等待策略，死循环（对延迟非常苛刻的场合可以考虑）
        Disruptor<PCData> disruptor = new Disruptor<PCData>(factory,
                bufferSize,
                threadFactory,
                ProducerType.MULTI,
                new BlockingWaitStrategy());

        disruptor.handleEventsWithWorkerPool(
                new Consumer(),
                new Consumer(),
                new Consumer(),
                new Consumer());
        disruptor.start();

        RingBuffer<PCData> ringBuffer = disruptor.getRingBuffer();
        Producer producer = new Producer(ringBuffer);
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            producer.pushData(bb);
            Thread.sleep(100);
            logger.info("add data " + l);
        }

    }
}
