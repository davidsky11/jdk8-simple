package com.kvlt.reflect.reflectAsm;

import net.sf.cglib.beans.BeanCopier;

/**
 * @author daishengkai
 * 2018-07-19 10:52
 */
public class Test3 {

    private int i;
    private int num;
    private int count;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static void main(String[] args) {
        BeanCopier bc = BeanCopier.create(Test1.class, Test3.class, false);
        Test1 t3 = new Test1();
        t3.setI(0);
        t3.setNum(1);

        Test3 t1 = new Test3();
        bc.copy(t3, t1, null);
        System.out.println(t1.toString());
    }
}
