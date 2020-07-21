package com.github.jiangxch.hasakei.config.annnotation;

import io.swagger.annotations.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: jiangxch
 * @date: 2020/7/22 上午1:42
 */


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Secured {
}
