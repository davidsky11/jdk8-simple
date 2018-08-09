package com.kvlt.swap;

import java.lang.reflect.Field;

/**
 * Demo
 *  按值传递、引用传递
 *
 * @author KVLT
 * @date 2018-08-09.
 */
public class Demo {

    public static void main(String[] args) {
        Integer a = 1, b = 2;

        System.out.println("before swap: a=" +a+ ",b="+b);
        swap(a, b);
        System.out.println("after swap: a=" +a+ ",b="+b);

        // 下面是错误示例
        int m = 3, n = 4;
        System.out.println("before swap: m=" +m+ ",n="+n);
        swap(m, n);

        // 下面在主方法区是有效的
        /*m = m + n;
        n = m - n;
        m = m - n;*/

        System.out.println("after swap: m=" +m+ ",n="+n);
    }

    private static void swap(int a, int b) {
        int tmp = a;
        a = b;
        b = tmp;
    }

    private static void swap1(int a, int b) {
        a = a + b;
        b = a - b;
        a = a - b;
    }

    private static void swap(Integer a, Integer b) {
        try {
            Field field = Integer.class.getDeclaredField("value");
            field.setAccessible(true);  // 不需要访问权限的检查（私有的成员属性是不能通过反射来赋值的）
            int tmp = a.intValue();
            field.set(a, b);
//            field.set(b, tmp);  // 结果不对，a=2,b=2

            // 装箱和拆箱
            // 主动New下，不走自动装箱操作
//            field.set(b, new Integer(tmp));  // 方法1

            // setInt，不产生装箱拆箱
            field.setInt(b, tmp);  // 方法2
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
