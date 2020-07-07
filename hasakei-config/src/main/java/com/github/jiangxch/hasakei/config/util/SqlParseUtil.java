package com.github.jiangxch.hasakei.config.util;

import com.github.jiangxch.hassakei.common.util.FileUtil;
import com.github.jiangxch.hassakei.common.util.JsonUtil;

/**
 * @author: jiangxch
 * @date: 2020/7/7 11:05
 */
public class SqlParseUtil {

    public static void main(String[] args) {
        String sql = FileUtil.readFile("db/config.sql");
        System.out.println(JsonUtil.toJson(sql));
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(sql);
    }

}
