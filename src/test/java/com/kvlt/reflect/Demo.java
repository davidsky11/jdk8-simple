package com.kvlt.reflect;

import com.kvlt.entity.Person;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @author KVLT
 * 2018-04-03 16:56
 */
public class Demo {

    @Test
    public void test1() throws Exception {
        Person p = new Person();

        String methodName = "getOid";
        System.out.println(String.class.getName());
            Method method = Person.class.getMethod(methodName);
            Object objField = method.invoke(p);  // 获取具体数据（没有区分数据类型）

            // 获取返回值类型
            Type type = method.getAnnotatedReturnType().getType();
        System.out.println(method.getReturnType().getTypeName());

            System.out.println(objField);
            System.out.println(type.getTypeName());

//            System.out.println("没有找到对应的方法：" + methodName);

    }

    private void getMethodTypeByName() {

    }
}
