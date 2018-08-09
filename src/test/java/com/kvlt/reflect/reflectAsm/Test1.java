package com.kvlt.reflect.reflectAsm;

/**
 * @author daishengkai
 * 2018-07-19 10:02
 */
public class Test1 {

    private int i;
    private int num;

    public Test1() {
    }

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

    @Override
    public String toString() {
        return "Test1{" +
                "i=" + i +
                ", num=" + num +
                '}';
    }
}
