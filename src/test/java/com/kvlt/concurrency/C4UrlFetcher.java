package com.kvlt.concurrency;

import akka.actor.UntypedAbstractActor;

/**
 * @author daishengkai
 * 2018-05-13 20:13
 */
public class C4UrlFetcher extends UntypedAbstractActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof C4Message) {
            C4Message work = (C4Message) message;
            String result = WS.url(work.url).get();
            getSender().tell(new C4Result(result), getSelf());
        } else {
            unhandled(message);
        }
    }

    public static void main(String[] args) {

    }
}
