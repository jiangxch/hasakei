package com.github.jiangxch.hasakei.raft.socket.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: jiangxch
 * @date: 2020/7/25 下午3:28
 */
@Data
public class BaseMessage<T> implements Serializable {
    /**  */
    private Integer type;
    private T data;
}
