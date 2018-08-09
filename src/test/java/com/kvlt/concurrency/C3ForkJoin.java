package com.kvlt.concurrency;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;

/**
 * 03、ForkJoin
 *      必须预见到可能产生的并发症
 * @author daishengkai
 * 2018-05-13 19:58
 */
public class C3ForkJoin {

    private static String getFirstResult(String question, List<String> engines) {
        Optional<String> result = engines.stream().parallel().map((base) -> {
            String url = base + question;
            return WS.url(url).get();
        }).findAny();
        return result.get();
    }

    public static void main(String[] args) {
        getFirstResult("Tell me Why?", Lists.newArrayList(new String[]{"e1", "e2", "e3", "e4", "e5"}));
    }
}
