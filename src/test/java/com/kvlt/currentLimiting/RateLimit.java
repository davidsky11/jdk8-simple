package com.kvlt.currentLimiting;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 令牌桶算法——Guava
 * @author daishengkai
 * 2018-05-14 10:36
 */
public class RateLimit {

    @Test
    public void limit1() {
        // 每秒向桶中放5个token
        final RateLimiter limit = RateLimiter.create(5);
        for (int i=0; i<10; i++) {
            // 从桶中获取1个token
            double waitTime = limit.acquire();
            System.out.println(waitTime);
        }
    }

    @Test
    public void limit2() {
        // 每秒向桶中放5个token
        final RateLimiter limit = RateLimiter.create(5);
        // 产生突发流量时，一次从桶中获取5个token
        System.out.println(limit.acquire(5));
        System.out.println(limit.acquire());
    }

    @Test
    public void limit3() {
        // 每秒向桶中放5个token（给系统一个缓冲时间，限流速率从慢速逐渐过渡到平均速率）
//        finalDemo RateLimiter limit = RateLimiter.create(5, 1, TimeUnit.SECONDS);
        final RateLimiter limit = RateLimiter.create(5);

        // 产生突发流量时，一次从桶中获取5个token
//        System.out.println(limit.acquire(5));

        // 尝试从桶中获取Token，获取不到不等待立即返回false
        boolean result = limit.tryAcquire();
        if (result) {
            System.out.println(" 1 成功获取到Token");
        }

        // 尝试从桶中获取Token，只等待10ms
        result = limit.tryAcquire(10, TimeUnit.MILLISECONDS);
        if (result) {
            System.out.println(" 2 成功获取到Token");
        }

    }

}
