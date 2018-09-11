package com.kvlt.lock.redis;

/**
 * RedisDistributedLock
 *  分布式锁的注意事项
 *      1、互斥性（独享锁）      在任意时刻只有一个客户端可以获取锁
 *      2、防死锁       即使有一个客户端在持有锁的期间崩溃而没有主动解锁，也能保证后续其他客户端能加锁
 *      3、持锁人解锁     加锁和解锁必须是同一个客户端，客户端自己不能把别人加的锁解了
 *      4、可重入       当一个客户端获取对象锁之后，这个客户端可以再次获取本对象上的锁
 *
 * @author KVLT
 * @date 2018-08-13.
 */
public class RedisDistributedLock {

    private static final String test = "TEST";

    public static final int i = 1;

    public static final int j = 3;

    private final String sfs;

    public RedisDistributedLock() {
        sfs = "1212";
    }

}
