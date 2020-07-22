package com.github.jiangxch.hasakei.config.arg;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: jiangxch
 * @date: 2020/7/22 上午11:06
 */
@Data
public class CreateConfigInfoArg implements Serializable {
    @NotBlank
    private String configName;
    @NotNull
    @
    private Integer environment;

    private String content;

    private String md5;

    private Date gmtCreate;

    private Date gmtModified;

    private String configDesc;

    private Integer userId;

    private String username;
}
