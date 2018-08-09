package com.kvlt.demo;

import java.io.BufferedReader;

/**
 * 在该类对象销毁时自动调用close方法，你可以在close方法关闭你想关闭的资源
 * @author daishengkai
 * 2018-05-14 14:15
 */
public class TryClose implements AutoCloseable {

    @Override
    public void close() throws Exception {
        System.out.println(" Custom cllose method...    close resources");

        // 业务处理  BufferedReader  close
        BufferedReader br = null;
    }
}
