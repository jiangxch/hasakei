package com.github.jiangxch.hasakei.config.arg;

import com.github.jiangxch.hasakei.config.enums.Environment;
import com.github.jiangxch.hasakei.config.validator.EnumType;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author: jiangxch
 * @date: 2020/7/20 下午9:02
 */
@Data
public class UpdateConfigInfoArg implements Serializable {
    @Min(value = 0)
    private Long id;
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
