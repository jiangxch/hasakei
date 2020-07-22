package com.github.jiangxch.hasakei.config.enums;

/**
 * @author: jiangxch
 * @date: 2020/7/22 上午11:07
 */
public enum Environment {
    DEV("开发环境",1),
    PROD("生产环境",2),
    ;

    private String envName;
    private Integer type;

    Environment(String envName, Integer type) {
        this.envName = envName;
        this.type = type;
    }
    public String getEnvName() {
        return envName;
    }

    public int getType() {
        return type;
    }
}
