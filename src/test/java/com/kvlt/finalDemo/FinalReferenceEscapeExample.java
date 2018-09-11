package com.kvlt.finalDemo;

/**
 * FinalReferenceEscapeExample
 * final引用不能从构造函数“逸出”
 * JMM对final域的重拍序规则保证了能安全读取final域时已经在构造函数中被正确的初始化了。但是如果在构造函数内将被构造函数的引用为其他线程可见，那么久存在对象引用在构造函数中逸出，final的可见性就不能保证。 其实理解起来很简单， 就是在其他线程的角度去观察另一个线程的指令其实是重拍序的。
 * @author KVLT
 * @date 2018-09-10.
 */
public class FinalReferenceEscapeExample {

    final int i;
    static FinalReferenceEscapeExample obj;

    public FinalReferenceEscapeExample () {
        i = 1;       //1写final域
        obj = this;  //2 this引用在此“逸出”  因为obj不是final类型的，所以不用遵守可见性
    }

    public static void writer() {
        new FinalReferenceEscapeExample ();
    }

    public static void reader() {
        if (obj != null) {                     //3
            int temp = obj.i;                 //4
        }
    }

}
