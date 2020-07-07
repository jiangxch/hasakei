package com.github.jiangxch.hassakei.common.util;

/**
 * @author: jiangxch
 * @date: 2020/7/7 11:20
 */
public class StringUtil {
    public static final String EMPTY = "";
    public static final String NULL = "null";

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isBlank(final CharSequence cs) {
        final int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
