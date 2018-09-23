package com.kvlt.proxy;

/**
 * TargetImpl
 *
 * @author KVLT
 * @date 2018-09-14.
 */
public class TargetImpl implements Target {

    @Override
    public int test(int i) {
        return i + 1;
    }

}
