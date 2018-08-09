package com.kvlt.bytecode;

/**
 * @author KVLT
 * 2018-04-03 11:46
 */
public class CountServiceImpl implements CountService {
    private int count = 0;

    public int count() {
        return count++;
    }

}
