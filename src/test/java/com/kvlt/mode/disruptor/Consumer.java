package com.kvlt.mode.disruptor;

import com.lmax.disruptor.WorkHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Consumer
 *
 * @author KVLT
 * @date 2018-09-23.
 */
public class Consumer implements WorkHandler<PCData> {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Override
    public void onEvent(PCData event) throws Exception {
        logger.info(Thread.currentThread().getId() + ":Event: --- " + event.get() * event.get() + " --- ");
    }
}
