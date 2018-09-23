package com.kvlt.proxy.staticModel;

public class AFactory implements AbstractFactory {

    @Override
    public void buysSomething() {
        System.out.println("I want to buy a car...");
    }

}