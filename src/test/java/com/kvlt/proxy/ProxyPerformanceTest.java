package com.kvlt.proxy;

import net.sf.cglib.core.DebuggingClassWriter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ProxyPerformanceTest
 * JDK动态代理的运行速度随着jdk版本的升级逐渐提高了
 *  1、在1.6和1.7中，JDK动态代理的速度比CGLIB动态代理速度慢
 *  2、在1.8中，JDK动态代理比CGLIB动态代理快
 * @author KVLT
 * @date 2018-09-14.
 */
public class ProxyPerformanceTest {

    static final String javaVersion = System.getProperty("java.version");

    public static void main(String[] args) {
        // 通过设置 DebuggingClassWriter.DEBUG_LOCATION_PROPERTY 的属性值来获取cglib生成的代理类
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "F:\\code");

        // 创建测试对象
        Target nativeTest = new TargetImpl();
        Target dynamicProxy = JdkDynamicProxyTest.newProxyInstance(nativeTest);  // 基于接口
        Target cglibProxy = CglibProxyTest.newProxyInstance(TargetImpl.class);  // 基于实现类

        // 预热一下
        int preRunCount = 10000;
        runWithoutMonitor(nativeTest, preRunCount);
        runWithoutMonitor(cglibProxy, preRunCount);
        runWithoutMonitor(dynamicProxy, preRunCount);

        // 执行测试
        Map<String, Target> tests = new LinkedHashMap<>();
        tests.put("Native", nativeTest);
        tests.put("Dynamic", dynamicProxy);
        tests.put("Cglib", cglibProxy);

        int repeatCount = 3;
        int runCount = 100000;
        runTest(repeatCount, runCount, tests);

        runCount = 1000000;
        runTest(repeatCount, runCount, tests);
        runCount = 5000000;
        runTest(repeatCount, runCount, tests);
    }

    private static void runTest(int repeatCount, int runCount, Map<String, Target> testss){
        System.out.println(String.format("\n======= run test : [repeatCount=%s] [runCount=%s] [java.version=%s] ======",
                repeatCount, runCount, javaVersion));
        for (int i = 0; i < repeatCount; i++) {
            System.out.println(String.format("\n-------- test : [%s] ---------", (i + 1)));
            for (String key : testss.keySet()) {
                runWithoutMonitor(testss.get(key), runCount, key);
            }
        }
    }

    private static void runWithoutMonitor(Target target, int runCount) {
        for (int i = 0; i < runCount; i++) {
            target.test(i);
        }
    }

    private static void runWithoutMonitor(Target target, int runCount, String tag) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < runCount; i++) {
            target.test(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("[" + tag + "] Total Time: " + (end - start) + "ms");
    }
}
