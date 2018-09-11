package com.kvlt.finalDemo;

/**
 * 初次读一个包含final域的对象的引用和随后初次写这个final域，不能重拍序。
 * 在构造函数内对final域写入，随后将构造函数的引用赋值给一个引用变量，操作不能重排序。
 */
public class FinalExample {

    int i;
    final int j;
    static FinalExample obj;

    public FinalExample() {
        i = 1;
        j = 2;
    }

    public static void writer() {  // 写线程
        obj = new FinalExample();
    }

    public static void reader() {  // 读线程B
        if (obj != null) {
            int a = obj.i;
            int b = obj.j;
        }
    }

    public static void main(String[] args) {

    }
}