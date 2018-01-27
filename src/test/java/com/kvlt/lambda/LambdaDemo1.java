package com.kvlt.lambda;

import com.kvlt.entity.Person;
import org.junit.After;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * LambdaDemo1
 *
 * @author KVLT
 * @date 2018-01-27.
 */
public class LambdaDemo1 {

    private List<Person> beanLs = GlobalParam.getBeanLs();

    @After
    public void after() {
        GlobalParam.toString(beanLs);
    }

    @Test
    public void simple0() {
        Optional<Person> tmp = beanLs.stream()
                .filter((Person p) -> "air".equals(p.getName())).findFirst();

        if (tmp.isPresent()) {
            System.out.println(tmp);
        }
    }

    @Test
    public void simple1() {
        Comparator<Person> byAge =
                (Person p1, Person p2) -> p1.getAge().compareTo(p2.getAge());
//        Comparator<Person> byName =
//                Comparator.comparing();
    }

    @Test
    public void simple2() {

    }

    @Test
    public void sort1() {
        Collections.sort(beanLs, (Person p1, Person p2) -> p1.getName().compareTo(p2.getName()));
    }

    @Test
    public void sort2() {
        Collections.sort(beanLs, (p1, p2) -> p1.getName().compareTo(p2.getName()));
    }


}
