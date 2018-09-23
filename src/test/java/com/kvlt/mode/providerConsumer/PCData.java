package com.kvlt.mode.providerConsumer;

/**
 * PCData
 *  不变模式
 * @author KVLT
 * @date 2018-09-23.
 */
public final class PCData {

    private final int intData;

    public PCData(int d) {
        intData = d;
    }

    public PCData(String d) {
        intData = Integer.valueOf(d);
    }

    public int getData() {
        return intData;
    }

    @Override
    public String toString() {
        return "data: " + intData;
    }
}
