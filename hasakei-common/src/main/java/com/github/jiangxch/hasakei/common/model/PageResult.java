package com.github.jiangxch.hasakei.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = -9190312489999541200L;

    private Integer pageNumber;

    private Integer pageSize;

    private Integer totalCount;

    private List<T> dataList;
}
