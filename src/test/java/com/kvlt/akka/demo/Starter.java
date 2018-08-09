package com.kvlt.akka.demo;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author daishengkai
 * 2018-05-14 10:23
 */
public class Starter {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("HelloSystem");
        final ActorRef sender = system.actorOf(Props.create(SenderActor.class), "sender");
        final ActorRef greeter = system.actorOf(Props.create(ReceiverActor.class), "greeter");
        greeter.tell(ReceiverActor.Msg.GREET, sender);
    }

}
