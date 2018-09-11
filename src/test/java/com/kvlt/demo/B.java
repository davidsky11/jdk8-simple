package com.kvlt.demo;

/**
 * B
 *
 * @author KVLT
 * @date 2018-08-29.
 */
public class B extends A {

    public B() {
        System.out.println("is B...");
    }

    public B(int i) {
        super(i);
        System.out.println("is B... " + i);
    }

    public static void main(String[] args) {
        B b = new B(3);
    }
}
