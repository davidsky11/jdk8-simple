package com.kvlt.akka.persistence;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author daishengkai
 * 2018-05-11 14:23
 */
public class ExampleState implements Serializable {

    private static final long serialVersionUID = 1L;
    private final ArrayList<String> events;

    public ExampleState() {
        this(new ArrayList<>());
    }

    public ExampleState(ArrayList<String> events) {
        this.events = events;
    }

    public ExampleState copy() {
        return new ExampleState(new ArrayList<>(events));
    }

    public void update(Evt evt) {
        events.add(evt.getData());
    }

    public int size() {
        return events.size();
    }

    @Override
    public String toString() {
        return events.toString();
    }

}
