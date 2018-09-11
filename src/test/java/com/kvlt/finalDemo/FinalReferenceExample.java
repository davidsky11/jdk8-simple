package com.kvlt.finalDemo;

/**
 * 在构造函数内对一个final引用的对象的成员域的写入和随后在构造函数外将被构造对象的引用赋值给引用变量之间不能重拍序。 即先写int[]数组的内容，再将引用抛出去。
 */
public class FinalReferenceExample {

    final int[] intArray;                     //final是引用类型
    static FinalReferenceExample obj;

    public FinalReferenceExample () {        //构造函数  在构造函数中不能被重排序 final类型在声明或者在构造函数中要赋值。
        intArray = new int[1];              //1
        intArray[0] = 1;                   //2
    }

    public static void writerOne () {          //写线程A执行
        obj = new FinalReferenceExample ();  //3
    }

    public static void writerTwo () {          //写线程B执行
        obj.intArray[0] = 2;                 //4
    }

    public static void reader () {              //读线程C执行
        if (obj != null) {                    //5
            int temp1 = obj.intArray[0];       //6
        }
    }

}