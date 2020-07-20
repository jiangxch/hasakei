package com.github.jiangxch.hasakei.config.util;

import org.springframework.core.io.ClassPathResource;

/**
 * @author: jiangxch
 * @date: 2020/7/7 11:05
 */
public class SqlParseUtil {

    public static void main(String[] args) {
            ClassPathResource r = new ClassPathResource("classpath*:META-INF/basic-mybatis-support-config.properties");
            System.out.println(r.exists());

//        String sql = FileUtil.readFile("db/config.sql");
//        System.out.println(JsonUtil.toJson(sql));
//        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        System.out.println(sql);
    }

}
