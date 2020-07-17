package com.github.jiangxch.hasakei.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: jiangxch
 * @date: 2020/7/6 11:34
 */
@SpringBootApplication
@MapperScan("com.github.jiangxch.hasakei.config.dao")
public class Config {
    public static void main(String[] args) {
        SpringApplication.run(Config.class,args);
    }
}
