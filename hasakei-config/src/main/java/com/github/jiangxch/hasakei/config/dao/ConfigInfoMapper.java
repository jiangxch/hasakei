package com.github.jiangxch.hasakei.config.dao;

import com.github.jiangxch.hasakei.config.arg.ListConfigInfoByPageArg;
import com.github.jiangxch.hasakei.config.dao.entity.ConfigInfo;
import com.github.jiangxch.mybatis.core.mapper.ICurdMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: jiangxch
 * @date: 2020/7/19 下午3:16
 */
public interface ConfigInfoMapper extends ICurdMapper<ConfigInfo> {
    List<ConfigInfo> listConfigInfoByPage(@Param("arg") ListConfigInfoByPageArg arg);
}