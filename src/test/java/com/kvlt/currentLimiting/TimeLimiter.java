package com.kvlt.currentLimiting;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 控制单位时间窗口内请求数
 * @author daishengkai
 * 2018-05-14 11:22
 */
public class TimeLimiter {

    private static Long permit = 50L;

    private LoadingCache<Long, AtomicLong> counter = CacheBuilder.newBuilder()
            .expireAfterWrite(2, TimeUnit.SECONDS)
            .build(new CacheLoader<Long, AtomicLong>() {
                @Override
                public AtomicLong load(Long seconds) throws Exception {
                    return new AtomicLong(0);
                }
            });

    @Test
    public void exec() throws Exception {

        AtomicLong idx = new AtomicLong(0);

        while (true) {
            // 当前秒
            Long currentSeconds = System.currentTimeMillis() / 1000;

            if (counter.get(currentSeconds).incrementAndGet() > permit) {

                if (idx.incrementAndGet() < 10) {
                    System.out.println("访问速率过快");
                    continue;
                } else {
                    System.out.println("截断访问");
                    break;
                }
            }

            // 业务处理
        }
    }
}
