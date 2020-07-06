package com.github.jiangxch.hasakei.config.repository;

import com.github.jiangxch.hasakei.config.arg.ConfigArg;

/**
 * 持久化配置信息service
 *
 * @author: jiangxch
 * @date: 2020/7/6 13:46
 */
public interface PersistService {

    /**
     * 插入或更新配置
     * @param configArg
     */
    boolean insertOrUpdateConfig(ConfigArg configArg);

    /**
     * 删除配置
     * @param configId
     */
    void deleteConfig(String configId);

    void findConfig(String configName,String configEnvironment);
}
