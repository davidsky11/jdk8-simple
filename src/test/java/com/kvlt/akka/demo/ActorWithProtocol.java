package com.kvlt.akka.demo;

import akka.actor.AbstractActorWithStash;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author daishengkai
 * 2018-05-11 14:16
 */
public class ActorWithProtocol extends AbstractActorWithStash {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("open", s -> {
                    getContext().become(receiveBuilder()
                            .matchEquals("write", ws -> {
                                /* do writing */
                                System.out.println("write into something...");
                            })
                            .matchEquals("read", rs -> {
                                /* do reading */
                                System.out.println("read something from...");
                            })
                            .matchEquals("close", cs -> {
                                unstashAll();
                                getContext().unbecome();
                            })
                            .matchAny(msg -> stash())
                            .build(), false);
                })
                .matchAny(msg -> stash())
                .build();
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("actorWithProtocol");
        ActorRef swapper = system.actorOf(Props.create(ActorWithProtocol.class), "actor");
        swapper.tell("open", ActorRef.noSender());
        swapper.tell("write", ActorRef.noSender());
        swapper.tell("read", ActorRef.noSender());
        swapper.tell("write", ActorRef.noSender());
        swapper.tell("read", ActorRef.noSender());
        swapper.tell("close", ActorRef.noSender());
        system.terminate();
    }
}
