package com.kvlt.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author daishengkai
 * 2018-07-11 11:42
 */
public class StringDemo {

    /**
     * 连接多个字符串并追加到StringBuilder
     */
    @Test
    public void stringJoin() {
        StringBuilder stringBuilder = new StringBuilder("hello");

        Joiner joiner = Joiner.on("|").useForNull(" ");

        stringBuilder = joiner.appendTo(stringBuilder, "foo", " ","bar", null, "car", "");
        System.out.println(stringBuilder);
    }

    /**
     * 连接List元素并写到文件流
     */
    @Test
    public void stringWriteToFile() {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(new File("C:\\Users\\david\\Desktop\\123112.txt"));
        } catch (Exception e) {

        }

        List<Date> dateList = new ArrayList<Date>();
        dateList.add(new Date());
        dateList.add(null);
        dateList.add(new Date());
        // 构造连接器：如果有null元素，替换为no string
        Joiner joiner2 = Joiner.on("#").useForNull("no string");
        try {
            // 将list的元素的tostring()写到fileWriter，是否覆盖取决于fileWriter的打开方式，默认是覆盖，若有true，则是追加
            joiner2.appendTo(fileWriter, dateList);
            // 必须添加close()，否则不会写文件
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * 将Map转化为字符串
     */
    @Test
    public void mapToString() {
        Map<String, String> testMap = Maps.newLinkedHashMap();
        testMap.put("Cookies", "12332");
        testMap.put("Content-Length", "30000");
        testMap.put("Date", "2016.12.16");
        testMap.put("Mime", "text/html");
        // 用:分割键值对，并用#分割每个元素，返回字符串
        String returnedString = Joiner.on("#").withKeyValueSeparator(":").join(testMap);
        System.out.println(returnedString); // Cookies:12332#Content-Length:30000#Date:2016.12.16#Mime:text/html
    }

    /**
     * 将字符串分割为Iterable
     */
    @Test
    public void splitter() {
        // 分割符为|，并去掉得到元素的前后空白
        Splitter sp = Splitter.on("|").trimResults();
        String str = "hello | world | | your | Name ";
        Iterable<String> ss = sp.split(str);
        for (String it : ss) {
            System.out.println(it);
        }
    }

    /**
     * 将字符串转化为Map
     */
    @Test
    public void stringToMap() {
        // 内部类的引用，得到分割器，将字符串解析为map
        String returnedString = "name:kevin#age:11#psw:111";
        Splitter.MapSplitter ms = Splitter.on("#").withKeyValueSeparator(':');
        Map<String, String> ret = ms.split(returnedString);
        for (String it2 : ret.keySet()) {
            System.out.println(it2 + " -> " + ret.get(it2));
        }
    }

    /**
     * 字符串工具类Strings
     */
    @Test
    public void strings() {
        System.out.println(Strings.isNullOrEmpty("")); // true
        System.out.println(Strings.isNullOrEmpty(null)); // true
        System.out.println(Strings.isNullOrEmpty("hello")); // false
        // 将null转化为""
        System.out.println(Strings.nullToEmpty(null)); // ""

        // 从尾部不断补充T只到总共8个字符，如果源字符串已经达到或操作，则原样返回。类似的有padStart
        System.out.println(Strings.padEnd("hello", 8, 'T')); // helloTTT

    }

    /**
     * 空白一一替换
     */
    @Test
    public void breakingWhitespace() {
        // 空白回车换行对应换成一个#，一对一换
        String stringWithLinebreaks = "hello world\r\r\ryou are here\n\ntake it\t\t\teasy";
        String s6 = CharMatcher.breakingWhitespace().replaceFrom(stringWithLinebreaks, '#');
        System.out.println(s6); // hello#world###you#are#here##take#it###easy
    }

    /**
     * 连续空白缩成一个字符
     */
    @Test
    public void whitespace() {
        // 将所有连在一起的空白回车换行字符换成一个#，倒塌
        String tabString = "  hello   \n\t\tworld   you\r\nare             here  ";
        String tabRet = CharMatcher.whitespace().collapseFrom(tabString, '#');
        System.out.println(tabRet); // #hello#world#you#are#here#

        // 在前面的基础上去掉字符串的前后空白，并将空白换成一个#
        String trimRet = CharMatcher.whitespace().trimAndCollapseFrom(tabString, '#');
        System.out.println(trimRet);// hello#world#you#are#here

    }

    /**
     * 保留数字
     */
    @Test
    public void retailFrom() {
        String letterAndNumber = "1234abcdABCD56789";
        // 保留数字
        String number = CharMatcher.javaDigit().retainFrom(letterAndNumber);
        System.out.println(number);// 123456789
    }

}
