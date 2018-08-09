package com.kvlt.akka.demo;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author daishengkai
 * 2018-05-11 14:09
 */
public class Swapper extends AbstractLoggingActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("t--", s -> {
                    log().info("Hi");
                    getContext().become(receiveBuilder().
                            matchEquals("t--", x -> {
                                log().info("Ho");
                                getContext().unbecome(); // resets the latest 'become' (just for fun)
                            }).build(), false); // push on top instead of replace
                }).build();
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("SwapperSystem");
        ActorRef swapper = system.actorOf(Props.create(Swapper.class), "swapper");
        swapper.tell("t--", ActorRef.noSender()); // logs Hi
        swapper.tell("t--", ActorRef.noSender()); // logs Ho
        swapper.tell("t--", ActorRef.noSender()); // logs Hi
        swapper.tell("t--", ActorRef.noSender()); // logs Ho
        swapper.tell("t--", ActorRef.noSender()); // logs Hi
        swapper.tell("t--", ActorRef.noSender()); // logs Ho
        system.terminate();
    }
}
