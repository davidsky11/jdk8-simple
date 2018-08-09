package com.kvlt.akka.persistence;

import akka.persistence.AbstractPersistentActor;

/**
 * @author daishengkai
 * 2018-05-11 14:31
 */
public class MyPersistentActor extends AbstractPersistentActor {

    @Override
    public String persistenceId() {
        return "my-stable-persistence-id";
    }

    private void handleCommand(String c) {
        persistAsync(String.format("evt-%s-1", c), e -> {
            getSender().tell(e, getSelf());
        });
        persistAsync(String.format("evt-%s-2", c), e -> {
            getSender().tell(e, getSelf());
        });

        deferAsync(String.format("evt-%s-3", c), e -> {
            getSender().tell(e, getSelf());
        });
    }

    @Override
    public Receive createReceiveRecover() {
        return receiveBuilder().
                match(String.class, this::handleCommand).build();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().
                match(String.class, this::handleCommand).build();
    }

}
