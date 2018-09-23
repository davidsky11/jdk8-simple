package com.kvlt.thread;

/**
 * BadSuspend
 * suspend() 在导致线程暂停的同时，并不会去释放任何资源。
 * 被挂起的线程，从线程状态上看，还是 Runnable，这会严重影响对系统当前状态的判断
 * @author KVLT
 * @date 2018-09-13.
 */
public class BadSuspend {

    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (u) {  // 持有u的对象锁
                System.out.println("in " + getName());
                Thread.currentThread().suspend();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.resume();
        t2.resume();  // t2处于 RUNNABLE 状态
        t1.join();
        t2.join();
    }

}
