package com.github.jiangxch.hasakei.raft.socket.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 协议规定
 * |----|----------------...省略|
 *  type       data
 *  4byte      剩余
 *
 * @author: jiangxch
 * @date: 2020/7/25 下午3:28
 */
@Data
@AllArgsConstructor
public class BaseMessage<T> implements Serializable {
    /**  */
    private Integer type;
    private T data;
}
