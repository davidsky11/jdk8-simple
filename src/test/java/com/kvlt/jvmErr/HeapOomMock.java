package com.kvlt.jvmErr;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出测试
 *      堆内存是 JVM 所有线程共享的部分，在虚拟机启动的时候就已经创建。所有的对象和数组都在堆上进行分配。这部分空间可通过 GC 进行回收。
 *      当申请不到空间时会抛出 OutOfMemoryError。
 * @author daishengkai
 * 2018-05-11 11:35
 */
public class HeapOomMock {

    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<byte[]>();
        int i = 0;
        boolean flag = true;
        while (flag) {
            try {
                i++;
                list.add(new byte[1024 * 1024]);//每次增加一个1M大小的数组对象
            } catch (Throwable e) {
                e.printStackTrace();
                flag = false;
                System.out.println("count=" + i);//记录运行的次数
            }
        }
    }

}
