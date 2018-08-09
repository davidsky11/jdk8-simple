package com.kvlt.concurrency;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

/**
 * 02、Executor和CompletionService
 *      使用API来管理一组线程
 *      需要精确的控制程序产生的线程数量，以及它们的精确行为
 * @author daishengkai
 * 2018-05-13 19:49
 */
public class C2Executors {

    private static String getFirstResultExecutors(String question, List<String> engines) {
        ExecutorCompletionService<String> service = new ExecutorCompletionService<>(Executors.newFixedThreadPool(5));

        for (String base : engines) {
            String url = base + question;
            service.submit(() -> {
                return WS.url(url).get();
            });
        }

        try {
            return service.take().get();
        } catch (InterruptedException | ExecutionException e) {
            return null;
        }

    }
}
