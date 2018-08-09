package com.kvlt.cpuCache;

/**
 * LightThread
 *
 * @author KVLT
 * @date 2018-08-09.
 */
public class LightThread extends Thread {

    SharingLong[] shares;
    int index;

    LightThread(SharingLong[] shares, int index) {
        this.shares = shares;
        this.index = index;
    }

    public void run() {
        for (int i = 0; i < 100000000; i++) {
            shares[index].v ++;
        }
    }
}
