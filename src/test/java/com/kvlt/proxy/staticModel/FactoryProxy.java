package com.kvlt.proxy.staticModel;

/**
 * FactoryProxy
 *
 * @author KVLT
 * @date 2018-09-12.
 */
public class FactoryProxy implements AbstractFactory {

    AbstractFactory factory = new AFactory();

    @Override
    public void buysSomething() {
        System.out.println("before...");
        factory.buysSomething();
        System.out.println("After...");
    }

}
