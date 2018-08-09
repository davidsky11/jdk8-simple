package com.kvlt.jvmErr;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * 永久代内存溢出测试
 * @author daishengkai
 * 2018-05-11 11:36
 */
public class PermGenOomMock {

    public static void main(String[] args) {
        URL url = null;
        List<ClassLoader> classLoaderList = new ArrayList<ClassLoader>();
        try {
            url = new File("/tmp").toURI().toURL();
            URL[] urls = {url};
            while (true) {
                ClassLoader loader = new URLClassLoader(urls);
                classLoaderList.add(loader);
                loader.loadClass("com.kvlt.jvmErr.Test");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
