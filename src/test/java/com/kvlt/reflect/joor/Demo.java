package com.kvlt.reflect.joor;

import com.kvlt.entity.Person;
import org.junit.Test;

import static org.joor.Reflect.*;

/**
 * @author KVLT
 * 2018-04-03 15:01
 */
public class Demo {

    @Test
    public void joor1() {
        String world = on("java.lang.String")  // Like Class.forName()
                .create("Hello World") // Call most specific matching constructor
                .call("substring", 6)  // Call most specific matching substring() method
                .call("toString")      // Call toString()
                .get();                // Get the wrapped object, in this case a String
        System.out.println(world);
    }

    @Test
    public void joor2() {
        Person p = new Person();

        Person pn = on(p).call("setName", "text").get();
        System.out.println(pn.toString());
    }
}
