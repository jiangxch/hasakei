package com.github.jiangxch.hasakei.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Paths;

/**
 * @author: jiangxch
 * @date: 2020/7/6 11:34
 */
@SpringBootApplication
@MapperScan("com.github.jiangxch.hasakei.config.dao")
public class Config {
    static {
        System.setProperty("driverClassName", "org.apache.derby.jdbc.EmbeddedDriver");
        System.setProperty("username", "hasakei");
        System.setProperty("password", "hasakei");
        System.setProperty("url", "jdbc:derby:"
                // /home/lanlan/hasakei/data/derby-data
//                + Paths.get(System.getProperty("user.home"), "hasakei","data","derby-data").toString());
                + Paths.get(System.getProperty("user.home"), "hasakei").toString()
//                + ";create=true"
                );
    }

    public static void main(String[] args) {
        SpringApplication.run(Config.class,args);
    }
}
