package com.kvlt.reflect.methodhandler;

import org.junit.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

/**
 * @author KVLT
 * 2018-04-03 15:48
 */
public class InvokePerformanceTest {

    public static int sCount;

    // static method
    public static void increaseStatic() {
        sCount++;
    }

    // instance method
    public void increase() {
        sCount++;
    }

    // get the method handler
    public MethodHandle getIncreaseHandler(Object obj, String name, boolean isStatic) throws Exception {
        MethodType type = MethodType.methodType(void.class);
        MethodHandle handler;
        if (isStatic) {
            handler = MethodHandles.lookup().findStatic(obj.getClass(), name, type);
        } else {
            handler = MethodHandles.lookup().findVirtual(obj.getClass(), name, type).bindTo(obj);
        }
        return handler;
    }

    // get the method
    public Method getIncreaseMethod(Object obj, String name, boolean isStatic) throws Exception {
        if (isStatic) {
            if (obj instanceof Class<?>) {
                return ((Class<?>) obj).getMethod(name);
            } else {
                return obj.getClass().getMethod(name);
            }
        } else {
            return obj.getClass().getMethod(name);
        }
    }

    @Test
    public void main() throws Throwable {
        InvokePerformanceTest test = new InvokePerformanceTest();

        int count = 100000000; // times
        long start, end, gap;

        start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            test.increase();
        }
        end = System.currentTimeMillis();
        gap = end - start;
        System.out.println("direct call: " + gap); // call directly

        MethodHandle mh = test.getIncreaseHandler(test, "increase", false);
        start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            mh.invoke();
        }
        end = System.currentTimeMillis();
        gap = end - start;
        System.out.println("method handler : " + gap); // call method by handler

        Method method = test.getIncreaseMethod(test, "increase", false);
        start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            method.invoke(test);
        }
        end = System.currentTimeMillis();
        gap = end - start;
        System.out.println("method invoke: " + gap); // call method by invocation

        start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            increaseStatic();
        }
        end = System.currentTimeMillis();
        gap = end - start;
        System.out.println("\nstatic direct call: " + gap); // call static method directly

        mh = test.getIncreaseHandler(test, "increaseStatic", true);
        start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            mh.invoke();
        }
        end = System.currentTimeMillis();
        gap = end - start;
        System.out.println("static method handler : " + gap); // call static method by handler

        method = test.getIncreaseMethod(test, "increaseStatic", true);
        start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            method.invoke(null);
        }
        end = System.currentTimeMillis();
        gap = end - start;
        System.out.println("static method invoke: " + gap); // call static method by invocation
    }
}
