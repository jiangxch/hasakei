package com.github.jiangxch.hasakei.config.arg;



/**
 * @author: jiangxch
 * @date: 2020/7/6 13:59
 */
public class ConfigArg {
    private String configName;
    private String environment;
    private String group;
    private String content;
    private String desc;
    private String type;

    public ConfigArg(String configName, String environment, String group, String content, String desc, String type) {
        this.configName = configName;
        this.environment = environment;
        this.group = group;
        this.content = content;
        this.desc = desc;
        this.type = type;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
