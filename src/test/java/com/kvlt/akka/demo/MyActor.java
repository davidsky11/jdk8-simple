package com.kvlt.akka.demo;

import akka.actor.AbstractActorWithTimers;

import java.time.Duration;

/**
 * @author daishengkai
 * 2018-05-11 14:07
 */
public class MyActor extends AbstractActorWithTimers {

    private static Object TICK_KEY = "TickKey";

    private static final class FirstTick {
    }

    private static final class Tick {
    }

    public MyActor() {
        getTimers().startSingleTimer(TICK_KEY, new FirstTick(), Duration.ofMillis(500));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(FirstTick.class, message -> {
                    // do something useful here
                    getTimers().startPeriodicTimer(TICK_KEY, new Tick(), Duration.ofSeconds(1));
                })
                .match(Tick.class, message -> {
                    // do something useful here
                })
                .build();
    }

}
