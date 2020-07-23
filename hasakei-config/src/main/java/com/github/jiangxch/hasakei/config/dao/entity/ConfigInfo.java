package com.github.jiangxch.hasakei.config.dao.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private String configDesc;

    private String username;

}