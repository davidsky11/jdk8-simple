package com.kvlt.mode.disruptor;

/**
 * PCData
 *
 * @author KVLT
 * @date 2018-09-23.
 */
public class PCData {
    private long value;

    public void set(long value) {
        this.value = value;
    }

    public long get() {
        return value;
    }
}
