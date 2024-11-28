package com.jd.cho.rule.engine.common.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义一个异常类，用于处理我们发生的业务异常。
 *
 * @author chenhonghao
 * @date 2020-03-12 11:01
 */
@Getter
@Setter
public class RetryException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected Integer errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public RetryException() {
        super();
    }

    public RetryException(CommonException commonException) {
        super(commonException.getErrorMsg());
        this.errorCode = commonException.getErrorCode();
        this.errorMsg = commonException.getErrorMsg();
    }

    public RetryException(CommonException commonException, Throwable cause) {
        super(commonException.getErrorMsg(), cause);
        this.errorCode = commonException.getErrorCode();
        this.errorMsg = commonException.getErrorMsg();
    }

    public RetryException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public RetryException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public RetryException(int errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
