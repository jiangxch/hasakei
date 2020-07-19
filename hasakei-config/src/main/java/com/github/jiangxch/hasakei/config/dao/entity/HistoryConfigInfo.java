package com.github.jiangxch.hasakei.config.dao.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author: jiangxch
 * @date: 2020/7/19 下午3:16
 */
@Data
public class HistoryConfigInfo implements Serializable {
    private Long id;

    private Long configInfoId;

    private String configName;

    private String environment;

    private String configInfo;

    private Integer version;

    private Date gmtCreate;

    private String username;

}