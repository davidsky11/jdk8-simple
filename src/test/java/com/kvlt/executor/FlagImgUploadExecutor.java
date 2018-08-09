package com.kvlt.executor;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author KVLT
 * 2018-03-08 15:03
 */
public class FlagImgUploadExecutor {

    private static Random random = new Random();

    // 模拟延迟
    public static void delay() {
        try {
            Thread.sleep(700L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 模拟图片上传
    private static String getUploadUrl(String path) {
        delay();
        return path + "_" + random.nextDouble() + "";
    }

    // 异步获取价格
    public static Future<String> getFlagImgUrlAsync(String path) {
        CompletableFuture<String> future = new CompletableFuture();
        new Thread(() -> {
            String flagUrl = getUploadUrl(path);
            future.complete(flagUrl);
        }).start();
        return future;
    }

    public static void doSomethingElse() {
        System.out.println("做其他的事情。。。");
    }

    // 调用服务
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Future<String> future = getFlagImgUrlAsync("test");
        long invocationTime = System.currentTimeMillis() - start;
        System.out.println("调用接口时间：" + invocationTime + "毫秒");

        doSomethingElse();

        try {
            String url = future.get();
            System.out.println(url);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        long retrievalTime = System.currentTimeMillis() - start;
        System.out.println("返回价格消耗时间：" + retrievalTime + "毫秒");
    }

}
