package com.kvlt.reference;

import org.junit.Test;

import java.lang.ref.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author daishengkai
 * 四个级别的引用：强引用、软引用、弱引用、虚引用
 * 2018-07-31 10:50
 */
public class ReferenceTest {

    private static ReferenceQueue<MyObject> queue = new ReferenceQueue<>();

    public static class MyObject {
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("MyObject's finalize called");
        }

        @Override
        public String toString() {
            return "I am MyObject";
        }
    }

    public static class CheckRefQueue implements Runnable {
        Reference<MyObject> obj = null;

        @Override
        public void run() {
            try {
                obj = (Reference<MyObject>) queue.remove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (obj != null) {
                System.out.println("Object for SoftReference is " + obj.get());
            }
        }
    }

    /**
     * 强引用  类似  Object obj = new Object();
     *  1. 强引用可以直接访问目标对象；
     *  2. 强引用锁指向的对象在任何时候都不会被系统回收。JVM宁愿抛出OOM异常也不回收强引用所指向的对象；
     *  3. 强应用可能导致内存泄露；
     */
    @Test
    public void finalRefrence() {

    }

    /**
     * 软引用     用来描述一些还有用但并非必须的对象。
     *      对于软引用关联着的对象，在系统将要发生内存溢出异常之前，将会把这些对象列进回收范围之中进行第二次回收。如果这次回收还没有足够的内存，才会抛出内存溢出异常。
     *      对于软引用关联着的对象，如果内存充足，则垃圾回收器不会回收该对象，如果内存不够了，就会回收这些对象的内存。
     *      软引用可用来实现内存敏感的高速缓存。软引用可以和一个引用队列（ReferenceQueue）联合使用，如果软引用所引用的对象被垃圾回收器回收，Java虚拟机就会把这个软引用加入到与之关联的引用队列中。
     */
    @Test
    public void softReference() {
        MyObject object = new MyObject();
        SoftReference<MyObject> softRef = new SoftReference<>(object, queue);
        new Thread(new CheckRefQueue()).start();

        object = null;    //删除强引用
        System.gc();
        System.out.println("After GC: Soft Get= " + softRef.get());
        System.out.println("分配大块内存");
        byte[] b = new byte[5 * 1024 * 928];
        System.out.println("After new byte[]:Soft Get= " + softRef.get());
        System.gc();
    }

    /**
     * 弱引用      用来描述非必须的对象
     *      它的强度比软引用更弱一些，被弱引用关联的对象只能生存到下一次垃圾收集发送之前。当垃圾收集器工作时，无论当前内存是否足够，
     *      都会回收掉只被弱引用关联的对象。一旦一个弱引用对象被垃圾回收器回收，便会加入到一个注册引用队列中。
     *
     *      弱引用一旦被发现，便会立即被回收
     */
    @Test
    public void weakReference() {
        MyObject object = new MyObject();
        Reference<MyObject> weakRef = new WeakReference<>(object, queue);
        System.out.println("创建的弱引用为：" + weakRef);
        new Thread(new CheckRefQueue()).start();

        object = null;
        System.out.println("Before GC: Weak Get= " + weakRef.get());
        System.gc();
        System.out.println("After GC: Weak Get= " + weakRef.get());
    }

    /**
     * 虚引用也称为幽灵引用或者幻影引用，它是最弱的一种引用关系
     *      虚引用的最大作用在于跟踪对象回收，清理被销毁对象的相关资源。
     *
     *      一个持有虚引用的对象，和没有引用几乎是一样的，随时都有可能被垃圾回收器回收。当试图通过虚引用的get()方法取得强引用时，总是会失败。并且，虚引用必须和引用队列一起使用，它的作用在于跟踪垃圾回收过程。
     */
    @Test
    public void phantomReference() throws Exception {
        MyObject object = new MyObject();
        Reference<MyObject> phanRef = new PhantomReference<>(object, queue);
        System.out.println("创建的虚引用为：" + phanRef);
        new Thread(new CheckRefQueue()).start();

        object = null;
        TimeUnit.SECONDS.sleep(1);
        int i = 1;
        while (true) {
            System.out.println("第" + i++ + "次gc");
            System.gc();
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
