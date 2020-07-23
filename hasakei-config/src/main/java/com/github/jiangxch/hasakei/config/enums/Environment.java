package com.github.jiangxch.hasakei.config.enums;

/**
 * @author: jiangxch
 * @date: 2020/7/22 上午11:07
 */
public enum Environment {
    DEV("开发环境",1),
    PROD("生产环境",2),
    ;

    private String name;
    private Integer type;

    Environment(String name, Integer type) {
        this.name = name;
        this.type = type;
    }
    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public static Environment getByType(int type) {
        for (Environment value : values()) {
            if (value.getType() == type) {
                return value;
            }
        }
        String msg = String.format("there is not this type=[%d]", type);
        throw new RuntimeException(msg);
    }
}
