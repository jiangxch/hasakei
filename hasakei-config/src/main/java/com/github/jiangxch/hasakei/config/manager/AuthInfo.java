package com.github.jiangxch.hasakei.config.manager;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: jiangxch
 * @date: 2020/7/22 上午2:34
 */
@Data
public class AuthInfo implements Serializable {
    private String username;
    private String token;
}
