package com.kvlt.guava;

import com.google.common.collect.HashBasedTable;
import org.junit.Test;

import java.util.Map;

/**
 * @author daishengkai
 * 2018-07-11 16:07
 */
public class TableDemo {

    /**
     * 两个键操作
     */
    @Test
    public void hashBasedTable() {
        // 两个键row key和column key，其实就是map中map, map<Integer, map<Integer, String> > mp
        HashBasedTable<Integer, Integer, String> table = HashBasedTable.create();
        table.put(1, 1, "book");
        table.put(1, 2, "turkey");
        table.put(2, 2, "apple");
        System.out.println(table.get(1, 1)); // book
        System.out.println(table.contains(2, 3)); // false
        System.out.println(table.containsRow(2)); // true
        table.remove(2, 2);
        System.out.println(table.get(2, 2)); // null

        // 获取单独的一个map
        Map<Integer, String> row = table.row(1);
        Map<Integer, String> column = table.column(2);
        System.out.println(row.get(1)); // book
        System.out.println(column.get(1)); // turkey
    }

}
