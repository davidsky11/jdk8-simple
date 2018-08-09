package com.kvlt.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author daishengkai
 * 2018-05-13 19:37
 */
public class WS {

    private AtomicInteger ai = new AtomicInteger(0);

    // 私有，静态
    private static WS uniqueInstance = null;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public WS() {}

    public static WS getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new WS();
        }
        return uniqueInstance;
    }

    private void init(String url) {
        if (uniqueInstance != null) {
            uniqueInstance.url = url;
        }
    }

    public static WS url(String url) {
        WS instance = getInstance();
        instance.setUrl(url);

        return instance;
    }

    public String get() {
        if (ai.getAndIncrement() < 100) {
            String s = "test_" + url + "_" + ai.get();
            return s;
        } else {
            return null;
        }
    }
}
