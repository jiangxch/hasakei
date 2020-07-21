package com.github.jiangxch.hasakei.config.manager;

/**
 * 用ThreadLocal存储RequestContext {@link AuthInfo}
 */
public class RequestContextHolder {

    private static final ThreadLocal<AuthInfo> THREAD_LOCAL = new ThreadLocal<>();

    public static void setContext(AuthInfo context) {
        THREAD_LOCAL.set(context);
    }

    /**
     * 获取用户信息上下文
     * @return
     */
    public static AuthInfo getContext() {
        return THREAD_LOCAL.get();
    }

    public static void clear() {
        THREAD_LOCAL.remove();
    }
}