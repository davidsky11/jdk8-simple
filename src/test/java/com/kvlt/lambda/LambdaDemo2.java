package com.kvlt.lambda;

import com.google.common.collect.Lists;
import com.kvlt.GlobalParam;
import org.junit.After;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LambdaDemo2
 *
 * @author KVLT
 * @date 2018-01-27.
 */
public class LambdaDemo2 {

    private List<String> strLs = GlobalParam.getStringLs();

    @After
    public void after() {
        GlobalParam.toString(strLs);
    }

    @Test
    public void sort1() {
        Collections.sort(strLs, Collections.reverseOrder().reversed());
    }

    @Test
    public void sort2() {
        strLs.add(null);
        strLs.sort(Comparator.nullsFirst(String::compareTo));
    }


}
