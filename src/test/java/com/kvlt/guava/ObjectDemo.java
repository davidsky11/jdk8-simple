package com.kvlt.guava;

import org.junit.Test;

import java.util.Objects;

/**
 * @author daishengkai
 * 2018-07-11 15:41
 */
public class ObjectDemo {

    @Test
    public void equalsTo() {
        boolean value = Objects.equals(null, "");
        System.out.println(value); // deafult value
    }
}
