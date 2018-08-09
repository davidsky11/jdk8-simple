package com.kvlt.concurrency;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 01、裸线程
 *      简单，多线程运行，共享内存通信
 *      过分关注线程的数量（耗费没存和时间）
 * @author daishengkai
 * 2018-05-13 19:35
 */
public class C1Thread {

    private static String getFirstResult(String question, List<String> engines) {
        AtomicReference<String> result = new AtomicReference<String>();
        for (String base : engines) {
            String url = base + question;
            new Thread(() -> {
                result.compareAndSet(null, WS.url("").get());
            }).start();
        }

        while (result.get() == null);
        return result.get();
    }

}
