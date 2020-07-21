package com.github.jiangxch.hasakei.common.util;

import com.google.common.base.Strings;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Objects;

public class ExceptionUtil {
    
    public static String getAllExceptionMsg(Throwable e) {
        Throwable cause = e;
        StringBuilder strBuilder = new StringBuilder();
        
        while (cause != null && !Strings.isNullOrEmpty(cause.getMessage())) {
            strBuilder.append("caused: ").append(cause.getMessage()).append(";");
            cause = cause.getCause();
        }
        
        return strBuilder.toString();
    }
    
    public static Throwable getCause(final Throwable t) {
        final Throwable cause = t.getCause();
        if (Objects.isNull(cause)) {
            return t;
        }
        return cause;
    }
    
    public static String getStackTrace(final Throwable t) {
        if (t == null) {
            return "";
        }
        
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final PrintStream ps = new PrintStream(out);
        t.printStackTrace(ps);
        ps.flush();
        return new String(out.toByteArray());
    }
}