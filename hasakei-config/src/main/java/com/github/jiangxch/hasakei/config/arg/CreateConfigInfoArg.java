package com.github.jiangxch.hasakei.config.arg;

import com.github.jiangxch.hasakei.config.enums.Environment;
import com.github.jiangxch.hasakei.config.validator.EnumType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
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
    @EnumType(value = Environment.class)
    private Integer environment;
    @NotNull
    private String content;
    @NotNull
    private String configDesc;
}
