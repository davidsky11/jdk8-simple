package com.kvlt.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * KvFactoryProxy
 *
 * @author KVLT
 * @date 2018-09-12.
 */
public class KvFactoryProxy implements InvocationHandler {

    private Object factory;

    public Object getFactory() {
        return factory;
    }

    public void setFactory(Object factory) {
        this.factory = factory;
    }

    // 通过Proxy获取动态代理的对象
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(factory.getClass().getClassLoader(), factory.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doSomethingBefore();
        Object ret = method.invoke(factory, args);
        doSomethingAfter();

        return ret;
    }

    public void doSomethingBefore() {
        System.out.println("before...");
    }

    public void doSomethingAfter() {
        System.out.println("after...");
    }
}
