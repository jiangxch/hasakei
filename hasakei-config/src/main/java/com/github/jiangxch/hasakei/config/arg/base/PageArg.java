package com.github.jiangxch.hasakei.config.arg.base;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

@Data
public class PageArg {
    /**
     * 当前页码
     */
    @Min(value = 1)
    private Integer pageNo;

    /**
     * 每页数量
     */
    @Range(min = 1,max = 100)
    private Integer pageSize;

    /** 排序字段 */
    private String field;

    /** 排序顺序(DESC降,ASC) */
    private boolean isAsc;
}