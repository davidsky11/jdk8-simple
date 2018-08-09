package com.kvlt.akka.demo;

import akka.actor.UntypedAbstractActor;

/**
 * @author daishengkai
 * 2018-05-14 10:20
 */
public class ReceiverActor extends UntypedAbstractActor {

    public static enum Msg {
        GREET, DONE
    }

    @Override
    public void onReceive(Object msg) {
        if (msg == Msg.GREET) {
            System.out.println("Hello World!");
            getSender().tell(Msg.DONE, getSelf());
        } else {
            unhandled(msg);
        }
    }

}
