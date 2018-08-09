package com.kvlt.akka.persistence;

import java.io.Serializable;

/**
 * @author daishengkai
 * 2018-05-11 14:22
 */
public class Cmd implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String data;

    public Cmd(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

}
