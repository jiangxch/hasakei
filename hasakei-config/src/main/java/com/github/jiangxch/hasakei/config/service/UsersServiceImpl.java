package com.github.jiangxch.hasakei.config.service;

import com.github.jiangxch.hasakei.common.model.PageResult;
import com.github.jiangxch.hasakei.common.model.Result;
import com.github.jiangxch.hasakei.config.arg.ListConfigInfoByPageArg;
import com.github.jiangxch.hasakei.config.arg.UpdateConfigInfoArg;
import com.github.jiangxch.hasakei.config.dao.ConfigInfoMapper;
import com.github.jiangxch.hasakei.config.dao.UsersMapper;
import com.github.jiangxch.hasakei.config.dao.entity.ConfigInfo;
import com.github.jiangxch.hasakei.config.dao.entity.Users;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: jiangxch
 * @date: 2020/7/20 下午8:44
 */
@Service
public class UsersServiceImpl {
    @Autowired
    private UsersMapper usersMapper;

    public Users getByUsername(String username) {
        return usersMapper.getByUsername(username);
    }

    public Users getByUsernameAndPassword(String username,String password) {
        if (Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password)) {
            return null;
        }
        return usersMapper.getByUsernameAndPassword(username, password);
    }
}
