package com.github.jiangxch.hasakei.config.dao.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author: jiangxch
 * @date: 2020/7/19 下午3:16
 */
@Data
public class ConfigInfo implements Serializable {
    private Long id;

    private String configName;

    private String environment;

    private String content;

    private String md5;

    private Date gmtCreate;

    private Date gmtModified;

    private String configDesc;

    private Integer userId;

    private String username;

}