package com.kvlt.reflect.introspector;

import com.kvlt.entity.Person;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * JDK中提供了对JavaBean进行操作的一些API，  内省
 * @author KVLT
 * 2018-04-03 14:29
 */
public class IntrospectorTest {

    /**
     * 各种类操作javabean
     */
    public static void main(String[] args) throws Exception {
        Person p = new Person("张三", 23);
        String propertyName = "name";
        //1.以下使用PropertyDescriptor操作javabean
        getProperty(p, propertyName);

        Object val = "凯奇";
        setProperty(p, propertyName, val);

        //2.以下使用BeanInfo操作javabean
        getPropertyByBeanInfo(p, propertyName);

        //3.以下使用BeanUtils操作javabean。
        BeanUtils.setProperty(p, propertyName, "李四");
        //这是属性链，birthday是Date类型的，Date中有setTime(long time)方法
        BeanUtils.setProperty(p, "birthday.time", "11111");
        System.out.println(BeanUtils.getProperty(p, "birthday.time"));
        //BeanUtils操作中参数是String类型的 返回结果也是String类型
        System.out.println(BeanUtils.getProperty(p, "birthday.time").getClass().getName());

        //4.PropertyUtils操作javabean
        PropertyUtils.setProperty(p, "name", "lily");
        PropertyUtils.setProperty(p, "age", 30);//传入是是int
        System.out.println(PropertyUtils.getProperty(p, "age").getClass().getName());//返回是Integer
        PropertyUtils.setProperty(p, "birthday.time", 10000);
        System.out.println(p);
    }

    public static void getPropertyByBeanInfo(Person p, String propertyName)
            throws IntrospectionException, IllegalAccessException,
            InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(p.getClass());
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        //遍历数组找到指定属性
        for (PropertyDescriptor pd : pds) {
            if (pd.getName().equals(propertyName)) {
                System.out.println(pd.getReadMethod().invoke(p));
                break;
            }
        }
    }

    //设置属性方法
    public static void setProperty(Person p, String propertyName, Object val)
            throws IntrospectionException, IllegalAccessException,
            InvocationTargetException {
        PropertyDescriptor pd1 = new PropertyDescriptor(propertyName, p.getClass());
        Method methodSet = pd1.getWriteMethod();
        methodSet.invoke(p, val);//调用set方法把name值设置为“张凯”
        System.out.println(p);
    }

    //得到属性方法
    private static void getProperty(Person p, String propertyName)
            throws IntrospectionException, IllegalAccessException,
            InvocationTargetException {
        //获取javaBean的属性描述
        PropertyDescriptor pd = new PropertyDescriptor(propertyName, p.getClass());
        //得到方法
        Method methodGet = pd.getReadMethod();
        System.out.println(methodGet.invoke(p));//调用方法
    }

}
