package com.kvlt.proxy.staticModel;

/**
 * Demo
 * 静态代理
 *  优点：简单
 *  缺点：扩展性差，可维护性差，侵入性强
 * @author KVLT
 * @date 2018-09-12.
 */
public class Demo {

    static AbstractFactory factory = new FactoryProxy();

    public static void main(String[] args) {
        factory.buysSomething();
    }

}
