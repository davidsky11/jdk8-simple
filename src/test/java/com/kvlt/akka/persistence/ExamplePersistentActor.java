package com.kvlt.akka.persistence;

import akka.actor.AbstractActor;
import akka.persistence.AbstractPersistentActor;
import akka.persistence.SnapshotOffer;

/**
 * @author daishengkai
 * 2018-05-11 14:24
 */
public class ExamplePersistentActor extends AbstractPersistentActor {

    private ExampleState state = new ExampleState();
    private int snapShotInterval = 1000;

    public int getNumEvents() {
        return state.size();
    }

    @Override
    public String persistenceId() {
        return "sample-id-1";
    }

    @Override
    public AbstractActor.Receive createReceiveRecover() {
        return receiveBuilder()
                .match(Evt.class, state::update)
                .match(SnapshotOffer.class, ss -> state = (ExampleState) ss.snapshot())
                .build();
    }

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(Cmd.class, c -> {
                    final String data = c.getData();
                    final Evt evt = new Evt(data + "-" + getNumEvents());
                    persist(evt, (Evt e) -> {
                        state.update(e);
                        getContext().getSystem().eventStream().publish(e);
                        if (lastSequenceNr() % snapShotInterval == 0 && lastSequenceNr() != 0)
                            // IMPORTANT: create a copy of snapshot because ExampleState is mutable
                            saveSnapshot(state.copy());
                    });
                })
                .matchEquals("print", s -> System.out.println(state))
                .build();
    }

}
