package com.jd.cho.rule.engine.common.exceptions;

import lombok.Getter;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Getter
public class BusinessException extends RuntimeException {
    private static final Integer DEFAULT_ERROR_CODE = -200;


    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private final Integer errorCode;


    public BusinessException() {
        super();
        this.errorCode = DEFAULT_ERROR_CODE;
    }

    public BusinessException(String errorMsg) {
        super(errorMsg);
        this.errorCode = DEFAULT_ERROR_CODE;
    }

    public BusinessException(Exception exception) {
        super(exception.getMessage());
        this.errorCode = DEFAULT_ERROR_CODE;
    }

    public BusinessException(Throwable throwable) {
        super(throwable.getMessage());
        this.errorCode = DEFAULT_ERROR_CODE;
    }

    public BusinessException(CommonException commonException) {
        super(commonException.getErrorMsg());
        this.errorCode = commonException.getErrorCode();
    }
}
