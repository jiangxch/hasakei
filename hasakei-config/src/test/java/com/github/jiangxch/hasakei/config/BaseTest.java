package com.github.jiangxch.hasakei.config;

import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.Paths;

/**
 * @author: jiangxch
 * @date: 2020/7/17 19:04
 */
public class BaseTest {
    @BeforeClass
    public static void beforeClass(){
//        System.setProperty("driverClassName", "org.h2.Driver");
//        System.setProperty("username", "sa");
//        System.setProperty("password", "");
//        System.setProperty("url", "jdbc:h2:mem:blog;DB_CLOSE_DELAY=-1");

        System.setProperty("driverClassName", "org.apache.derby.jdbc.EmbeddedDriver");
        System.setProperty("username", "hasakei");
        System.setProperty("password", "hasakei");
        System.setProperty("url", "jdbc:derby:"
                // /home/lanlan/hasakei/data/derby-data
//                + Paths.get(System.getProperty("user.home"), "hasakei","data","derby-data").toString());
                + Paths.get(System.getProperty("user.home"), "hasakei").toString()
//                + ";create=true"
        );
        System.out.println(System.getProperty("url"));
    }


    public static void main(String[] args) {
        System.setProperty("driverClassName", "org.apache.derby.jdbc.EmbeddedDriver");
        System.setProperty("username", "hasakei");
        System.setProperty("password", "hasakei");
        System.setProperty("url", "jdbc:derby:"
                // /home/lanlan/hasakei/data/derby-data
//                + Paths.get(System.getProperty("user.home"), "hasakei","data","derby-data").toString());
                + Paths.get(System.getProperty("user.home"), "hasakei").toString()
                + ";create=true");
        System.out.println(System.getProperty("url"));
    }
}
