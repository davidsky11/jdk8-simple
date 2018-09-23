package com.kvlt.future;

import com.google.common.util.concurrent.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * ListenableFutureDemo
 *
 * @author KVLT
 * @date 2018-09-21.
 */
public class ListenableFutureDemo {

    private static Logger logger = LoggerFactory.getLogger(ListenableFutureDemo.class);

    // CPU核数
    private static final int processors = Runtime.getRuntime().availableProcessors();

    // 线程池中线程个数
    private static final int POOL_SIZE = 50;

    // 带有回调机制的线程池
    private static final ListeningExecutorService service = MoreExecutors.
            listeningDecorator(Executors.newFixedThreadPool(POOL_SIZE));

    private static final ListeningExecutorService service1 = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(POOL_SIZE));

    private static final ThreadFactory threadFactory =
            new ThreadFactoryBuilder()
                    .setDaemon(true)
                    .setNameFormat("ListenableFutureAdapter-thread-%d")
                    .build();

    private static final ExecutorService defaultAdapterExecutor = Executors.newFixedThreadPool(processors, threadFactory);

    @Test
    public void testListenableFuture() {
        final List<String> resultList = Collections.synchronizedList(new ArrayList<String>());

        try {
            List<ListenableFuture<String>> futures = new ArrayList<>();

            // 将实现了callable的任务放入到线程池中，得到一个带有回调机制的ListenableFuture实例
            // 通过Futures.addCallback方法对得到的ListenableFuture实例进行监听，一旦得到结果就进入到onSuccess方法
            // 在onSuccess方法中将查询的结果存入到集合中
            for (int i = 0; i < 10; i++) {
                final int index = i + 1;
                if (index % 3 == 0) {
                    Thread.sleep(500 * index);
                }

                ListenableFuture<String> futureInstance = service.submit(() -> {
                        long time = System.currentTimeMillis();
                        logger.info("{} Finishing sleeping task{}: {}", index, index, time);
                        return String.valueOf(time);
                });

                /*futureInstance = ListenableFutureTask.create(() -> {
                    long time = System.currentTimeMillis();
                    logger.info("Finishing sleeping task{}: {}", index, time);
                    return String.valueOf(time);
                });*/

                futureInstance.addListener(() -> logger.info("{} Listener be triggered for task{}.", index, index), service);

                Futures.addCallback(futureInstance, new FutureCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        logger.info("{} Add result value into value list {}.", index, result);
                        resultList.add(result);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        logger.info("{} Add result value into value list error.", index, t);
                        throw new RuntimeException(t);
                    }
                });

                // 将每一次查询得到的ListenableFuture放入到集合中
                futures.add(futureInstance);
            }

            // 这里将集合中的若干ListenableFuture形成一个新的ListenableFuture
            // 目的是为了异步阻塞，直到所有的ListenableFuture都得到结果才继续当前线程
            // 这里的时间取得是所有任务中用时最长的一个
            ListenableFuture<List<String>> allAsList = Futures.allAsList(futures);
            allAsList.get();
            logger.info("All sub-task are finished.");
        } catch (Exception ignored) {

        }
    }

    @Test
    public void testThreadFactory() throws Exception {
        String str = "test";

        Future<String> future = defaultAdapterExecutor.submit(new KvTask(str));

        ListenableFuture<String> listenInPoolThread = JdkFutureAdapters.listenInPoolThread(future);
        Futures.addCallback(listenInPoolThread, new FutureCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.printf("Success", result);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.printf("failure");
                    }
                }
        );

        future.get();
    }

    private static class KvTask implements Callable {
        private final String data;

        public KvTask(String data) {
            this.data = data;
        }

        @Override
        public String call() throws Exception {
            try {
                return "result_Success" + data;
            } catch (Exception e) {
                return "result-Failure" + data;
            }
        }
    }
}
