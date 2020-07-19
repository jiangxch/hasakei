package com.github.jiangxch.hasakei.config.dao;

import com.github.jiangxch.hasakei.config.dao.entity.Users;
import com.github.jiangxch.mybatis.core.mapper.ICurdMapper;

import java.util.List;

/**
 * @author: jiangxch
 * @date: 2020/7/19 下午3:16
 */
public interface UsersMapper extends ICurdMapper<Users> {
    List<Users> aaa();
}