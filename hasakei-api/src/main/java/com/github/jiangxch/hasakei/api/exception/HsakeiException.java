package com.github.jiangxch.hasakei.api.exception;

import com.github.jiangxch.hasakei.api.constant.Constants;
import com.github.jiangxch.hasakei.api.util.StringUtil;

public class HsakeiException extends Exception {
    
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -3913902031489277776L;
    
    private int errCode;
    
    private String errMsg;
    
    private Throwable causeThrowable;
    
    public HsakeiException() {
    }
    
    public HsakeiException(final int errCode, final String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
    
    public HsakeiException(final int errCode, final Throwable throwable) {
        super(throwable);
        this.errCode = errCode;
        this.setCauseThrowable(throwable);
    }
    
    public HsakeiException(final int errCode, final String errMsg, final Throwable throwable) {
        super(errMsg, throwable);
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.setCauseThrowable(throwable);
    }
    
    public int getErrCode() {
        return this.errCode;
        }
    
    public String getErrMsg() {
        if (!StringUtil.isBlank(this.errMsg)) {
            return this.errMsg;
        }
        if (this.causeThrowable != null) {
            return this.causeThrowable.getMessage();
        }
        return Constants.EMPTY;
    }
    
    public void setErrCode(final int errCode) {
        this.errCode = errCode;
    }
    
    public void setErrMsg(final String errMsg) {
        this.errMsg = errMsg;
    }
    
    public void setCauseThrowable(final Throwable throwable) {
        this.causeThrowable = this.getCauseThrowable(throwable);
    }
    
    private Throwable getCauseThrowable(final Throwable t) {
        if (t.getCause() == null) {
            return t;
        }
        return this.getCauseThrowable(t.getCause());
    }
    
    @Override
    public String toString() {
        return "ErrCode:" + getErrCode() + ", ErrMsg:" + getErrMsg();
    }
}