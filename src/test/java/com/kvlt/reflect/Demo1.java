package com.kvlt.reflect;

import java.lang.reflect.Method;

import org.junit.Test;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.esotericsoftware.reflectasm.FieldAccess;
import com.esotericsoftware.reflectasm.MethodAccess;

/**
 * @author daishengkai
 * 2018-05-18 15:56
 */
public class Demo1 {

    private static final Integer count = 1;  // 100000000

    public static void main(String[] args) throws Exception {
        int i=0;
        for (int k=0; k<1000000; k++) {
            i++;
        }
        /*Demo1 test = new Demo1();
        test.testJdkReflect();
        test.testReflectAsm4Name();
        test.testReflectAsm4Index();*/
    }

    /**
     * JDK反射调用方法
     *
     * @throws Exception
     */
    @Test
    public void testJdkReflect() throws Exception {
        UserService target = new UserService();
        long start = System.currentTimeMillis();
        Method method = target.getClass().getMethod("update", int.class, String.class);
        for (int i = 0; i < count; i++) {
            method.invoke(target, 1, "zhangsan");
        }
        long end = System.currentTimeMillis();
        System.out.println("timeout=" + (end - start));//809 753 880 875 816
    }

    /**
     * ReflectAsm反射调用方法
     * 用名称定位反射方法
     */
    @Test
    public void testReflectAsm4Name() {
        UserService target = new UserService();
        MethodAccess access = MethodAccess.get(UserService.class);//生成字节码的方式创建UserServiceMethodAccess
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            access.invoke(target, "update", 1, "zhangsan");
        }
        long end = System.currentTimeMillis();
        System.out.println("timeout=" + (end - start));//523 382 415 489 482
    }

    /**
     * ReflectAsm反射调用方法
     * 用方法和字段的索引定位反射方法，性能高
     */
    @Test
    public void testReflectAsm4Index() {
        UserService target = new UserService();
        MethodAccess access = MethodAccess.get(UserService.class);
        int index = access.getIndex("update", int.class, String.class);
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            access.invoke(target, index, 1, "zhangsan");
        }
        long end = System.currentTimeMillis();
        System.out.println("timeout=" + (end - start));//12 15 23 14 24
    }

    /**
     * ReflectAsm反射来set/get字段值
     */
    @Test
    public void testFieldAccess() {
        UserService target = new UserService();
        FieldAccess fieldAccess = FieldAccess.get(target.getClass());
        fieldAccess.set(target, "state", 1);
        int state = (Integer) fieldAccess.get(target, "state");
        System.out.println(state);

    }

    /**
     * ReflectAsm反射来调用构造方法
     */
    @Test
    public void testConstructorAccess() {
        ConstructorAccess<UserService> constructorAccess = ConstructorAccess.get(UserService.class);
        UserService userService = constructorAccess.newInstance();
        System.out.println(userService);
    }

    /**
     * 查找方法的索引
     */
    @Test
    public void testIndex() {
        UserService target = new UserService();
        MethodAccess methodAccess = MethodAccess.get(target.getClass());
        int index = methodAccess.getIndex("update", int.class, String.class);
        System.out.println(index);
    }

    @Test
    public void newInstance1() throws Exception {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            new UserService();
        }
        long end = System.currentTimeMillis();
        System.out.println("timeout=" + (end - start));//12 15 23 14 24
    }

    @Test
    public void newInstance2() throws Exception {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            UserService.class.newInstance();
        }
        long end = System.currentTimeMillis();
        System.out.println("timeout=" + (end - start));//12 15 23 14 24
    }
}