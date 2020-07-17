package com.github.jiangxch.hasakei.config;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author: jiangxch
 * @date: 2020/7/17 19:04
 */
public class BaseTest {
    @BeforeClass
    public static void beforeClass(){
        System.setProperty("driverClassName", "org.h2.Driver");
        System.setProperty("username", "sa");
        System.setProperty("password", "");
        System.setProperty("url", "jdbc:h2:mem:blog;DB_CLOSE_DELAY=-1");
    }
}
