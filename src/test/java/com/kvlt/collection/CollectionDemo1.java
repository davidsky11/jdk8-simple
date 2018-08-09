package com.kvlt.collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author daishengkai
 * 2018-04-16 14:31
 */
public class CollectionDemo1 {

    /**
     * 统计集合中每个元素出现的所有位置
     * @param list
     * @return
     */
    public static Map<String, List<Integer>> getElementPositions(List<String> list) {
        Map<String, List<Integer>> positionsMap = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            positionsMap.computeIfAbsent(list.get(i), k -> new ArrayList<>(1)).add(i);
        }

        return positionsMap;
    }

    public static String formatList(List<String> list, String delimiter) {
        StringJoiner result = new StringJoiner(delimiter);
        for (String str : list) {
            result.add(str);
        }
        return result.toString();
    }

    public static String formatList(List<String> list, String delimiter, String prefix, String suffix) {
        StringJoiner result = new StringJoiner(delimiter, prefix, suffix);
        for (String str : list) {
            result.add(str);
        }
        return result.toString();
    }

    public static String formatList2(List<String> list, String delimiter, String prefix, String suffix) {
        return list.stream().collect(Collectors.joining(delimiter, prefix, suffix));
    }

    @Test
    public void ttt() {
        List<Commission> ls = Lists.newArrayList();
        Commission c1 = new Commission(1L, BigDecimal.ZERO);
        Commission c2 = new Commission(2L, BigDecimal.ONE);
        Commission c3 = new Commission(3L, BigDecimal.TEN);
        Commission c4 = new Commission(4L, BigDecimal.ZERO);
        Commission c5 = new Commission(3L, null);
        Commission c6 = new Commission(4L, BigDecimal.TEN);
        ls.add(c1);
        ls.add(c2);
        ls.add(c3);
        ls.add(c4);
        ls.add(c5);
        ls.add(c6);

        Map<Long, BigDecimal> skuId2MaxcommissionMap = Maps.newHashMap();
//        ls.forEach((comm) -> skuId2MaxcommissionMap.put(comm.getId(), comm.getMaxCommission()));

        /*skuId2MaxcommissionMap = ls.stream().collect(Collector.of(HashMap::new, (m, per) -> m.put(per.getId(), per.getMaxCommission()),
                (k, v) -> k, Collector.Characteristics.IDENTITY_FINISH));*/
        skuId2MaxcommissionMap = ls.stream().collect(HashMap::new, (m, per) -> m.put(per.getId(), per.getMaxCommission()), HashMap::putAll);
        skuId2MaxcommissionMap.forEach((k,v) -> {
            System.out.println(k + " --> " + v);
        });
    }

    public static void main(String[] args) {
        // 1、删除元素
        List<String> ls = Lists.newArrayList();
        ls.add("test");
        ls.add("ta");
        ls.add("tc");
        ls.add("tb");
        ls.add("tewg");

        ls.removeIf(l -> l.startsWith("te"));
        System.out.println(Objects.toString(ls));

        // 2、统计元素位置
        List<String> list = Arrays.asList("a", "b", "b", "c", "c", "c", "d", "d", "d", "f", "f", "g");

        System.out.println("使用 computeIfAbsent 和 Iterable.forEach：");
        Map<String, List<Integer>> elementPositions = getElementPositions(list);
        System.out.println(elementPositions);

        // 3.1、字符串连接
        list = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

        System.out.println("使用 StringJoiner：");
        String format = formatList(list, ", ");
        System.out.println(format);

        // 3.2、字符串连接
        System.out.println("使用 StringJoiner，带前缀和后缀：");
        format = formatList(list, ", ", "{ ", " }");
        System.out.println(format);

        // 3.3、字符串连接
        System.out.println("使用 Collectors.joining：");
        format = formatList(list, ", ", "{ ", " }");
        System.out.println(format);

        // 3.4、字符串连接
        System.out.println("使用 String.join：");
        System.out.println(String.join(",", list));

        // 4、
        Optional<Integer> collectMaxBy = Stream.of(1, 2, 3, 4)
                .collect(Collectors.maxBy(Comparator.comparingInt(o -> o)));
        System.out.println("collectMaxBy:" + collectMaxBy.get());

        // 分隔数据块
        Map<Boolean, List<Integer>> collectParti = Stream.of(1, 2, 3, 4)
                .collect(Collectors.partitioningBy(it -> it % 2 == 0));
        System.out.println("collectParti : " + collectParti);

        // 数据分组
        Map<Boolean, List<Integer>> collectGroup = Stream.of(1, 2, 3, 4)
                .collect(Collectors.groupingBy(it -> it > 3));
        System.out.println("collectGroup : " + collectGroup);
        // 打印结果
        // collectGroup : {false=[1, 2, 3], true=[4]}

        Map<Boolean, Long> partiCount = Stream.of(1, 2, 3, 4)
                .collect(Collectors.partitioningBy(it -> it.intValue() % 2 == 0,
                        Collectors.counting()));
        System.out.println("partiCount: " + partiCount);
        // 打印结果
        // partiCount: {false=2, true=2}
    }

    private static class Commission {
        private Long id;
        private BigDecimal maxCommission;

        public Commission() {}

        public Commission(Long id, BigDecimal maxCommission) {
            this.id = id;
            this.maxCommission = maxCommission;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public BigDecimal getMaxCommission() {
            return maxCommission;
        }

        public void setMaxCommission(BigDecimal maxCommission) {
            this.maxCommission = maxCommission;
        }
    }
}


