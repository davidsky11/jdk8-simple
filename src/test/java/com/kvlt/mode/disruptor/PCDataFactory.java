package com.kvlt.mode.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * PCDataFactory
 *
 * @author KVLT
 * @date 2018-09-23.
 */
public class PCDataFactory implements EventFactory<PCData> {

    public PCData newInstance() {
        return new PCData();
    }
}
