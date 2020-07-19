package com.github.jiangxch.hasakei.config.dao.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @author: jiangxch
 * @date: 2020/7/19 下午3:16
 */
@Data
public class Users implements Serializable {
    private String username;
    private String password;
    private Boolean enabled;
}