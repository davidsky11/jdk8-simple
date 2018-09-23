package com.kvlt.future;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFutureDemo
 *
 * @author KVLT
 * @date 2018-09-21.
 */
public class CompletableFutureDemo {

    private static final Logger logger = LoggerFactory.getLogger(CompletableFutureDemo.class);

    // 线程池中线程个数
    private static final int POOL_SIZE = 50;

    @Test
    public void testCompletableFuture() throws Exception {
        // case1: supplyAsync
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            logger.info("Run supplyAsync. ");
            return "Return result of Supply Async";
        });

        // case2: thenRun，与supplyAsync同线程
        future.thenRun(() -> {
            logger.info("Run action. ");
        });

        // case2: thenRunAsync，另启动线程执行
        future.thenRunAsync(() -> {
            logger.info("Run async action. ");
        });

        // 主动触发Complete结束方法
//        future.complete("Manual complete value. ");
        future.whenComplete((v, e) -> {
            logger.info("WhenComplete value: " + v);
            logger.info("WhenComplete exception: " + e);
        });

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            logger.info("Return result of Run Async. ");
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return "hello";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            return "world";
        });

        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
            return "s-kv";
        });

        CompletableFuture<String> f = future2
                .thenCombine(future3, (x, y) -> x + "-" + y)
                .thenCombine(future4, (x, y) -> x + "," + y);
        logger.info(f.get());
    }

}
