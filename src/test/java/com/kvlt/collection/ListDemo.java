package com.kvlt.collection;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author daishengkai
 * 2018-06-12 17:11
 */
public class ListDemo {

    private static Logger logger = LoggerFactory.getLogger(ListDemo.class);

    private static AtomicInteger ai = new AtomicInteger(0);

    private static StackTraceElement[] trace;
    private static StackTraceElement tmp;

    private static void printLine(Object obj) {
        if (Objects.isNull(obj))    return;

        trace = new Throwable().getStackTrace();
        // 下标为0的元素是上一行语句的信息, 下标为1的才是调用printLine的地方的信息
        tmp = trace[1];

//        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        // 注意!这里是下标为2的,而不是为1的
//        StackTraceElement tmp = trace[2];

        String str = obj.toString();

        if (obj instanceof List) {
            str = Arrays.toString(((List) obj).toArray());
        }

        logger.info("{} - {}", tmp.getLineNumber(), str);
    }

    @Test
    public void testArrayList() {

    }

    @Test
    public void testLinkedList() {
        LinkedList<String> stringLinkedList = Lists.newLinkedList();
        stringLinkedList.add("able");
        stringLinkedList.add("bear");
        stringLinkedList.add("carry");

        printLine(stringLinkedList);
        printLine(stringLinkedList.peek());

        stringLinkedList.addFirst("000");
        printLine(stringLinkedList);

        stringLinkedList.addLast("end");
        printLine(stringLinkedList);

        stringLinkedList.add(2, "try");
        printLine(stringLinkedList);

        stringLinkedList.remove();
        printLine(stringLinkedList);

        stringLinkedList.remove("bear");
        printLine(stringLinkedList);

        stringLinkedList.remove(2);
        printLine(stringLinkedList);

        stringLinkedList.removeFirst();
        printLine(stringLinkedList);

        stringLinkedList.removeLast();
        printLine(stringLinkedList);

        stringLinkedList.clear();
        stringLinkedList.add("first");
        stringLinkedList.add("second");
        stringLinkedList.add("third");
        printLine(stringLinkedList);

        stringLinkedList.removeFirstOccurrence("first");  // 移除链表中第一次出现所在位置的元素
        printLine(stringLinkedList);

        stringLinkedList.removeLastOccurrence("first");  // 移除链表中最后一次出现所在位置的元素
        printLine(stringLinkedList);

        printLine(stringLinkedList.get(1));
        printLine(stringLinkedList.getFirst());
        printLine(stringLinkedList.getLast());

        stringLinkedList.push("four");  // 与 addFirst 一样，实际上它就是 addFirst
        stringLinkedList.push("five");
        stringLinkedList.push("six");

        printLine(stringLinkedList);

        stringLinkedList.pop();  // 链表为空，pop会产生异常
        printLine(stringLinkedList);

        printLine(stringLinkedList.poll());  // 查询并移除第一个元素

        printLine(stringLinkedList.peek());  // 获取第一个元素，但是不移除
        printLine(stringLinkedList.peekFirst());  // 获取第一个元素，但是不移除
        printLine(stringLinkedList.peekLast());  // 获取最后一个元素，但是不移除

        stringLinkedList.offer("seven");  // 在链表尾部插入一个元素
        printLine(stringLinkedList);

        stringLinkedList.offerFirst("000");  // 与 addFirst 一样，实际上它就是 addFirst
        printLine(stringLinkedList);

        stringLinkedList.offerLast("eeee");  // 与 addLast 一样，实际上它就是 addLast
        printLine(stringLinkedList);

        printLine(stringLinkedList.contains("six"));
        printLine(stringLinkedList.element());
        printLine(stringLinkedList.set(0, "010"));
        printLine(stringLinkedList.subList(0, 3));
    }

}
