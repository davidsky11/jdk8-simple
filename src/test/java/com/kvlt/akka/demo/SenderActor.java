package com.kvlt.akka.demo;

import akka.actor.UntypedAbstractActor;

/**
 * @author daishengkai
 * 2018-05-14 10:19
 */
public class SenderActor extends UntypedAbstractActor {

    @Override
    public void onReceive(Object msg) {
        System.out.println("received done ");

        if (msg == ReceiverActor.Msg.DONE) {
            getContext().stop(getSelf());
            getContext().getSystem().terminate();
        } else {
            unhandled(msg);
        }
    }
}
