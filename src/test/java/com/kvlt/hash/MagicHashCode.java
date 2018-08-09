package com.kvlt.hash;

/**
 * @author KVLT
 * 2018-03-27 10:17
 */
public class MagicHashCode {

    // ThreadLocal中定义的hash魔数
    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) {
        hashCode(16);//初始化16
        hashCode(32);//后续2倍扩容
        hashCode(64);
    }

    /**
     * @param length table长度
     * @Description 寻找散列下标（对应数组小标）
     * @author diandian.zhang
     * @date 2017年12月6日上午10:36:53
     * @since JDK1.8
     */
    private static void hashCode(Integer length) {
        int hashCode = 0;
        for (int i = 0; i < length; i++) {
            hashCode = i * HASH_INCREMENT + HASH_INCREMENT;//每次递增HASH_INCREMENT
            System.out.print(hashCode & (length - 1));//求散列下标，算法公式
            System.out.print(" ");
        }
        System.out.println();
    }
}
