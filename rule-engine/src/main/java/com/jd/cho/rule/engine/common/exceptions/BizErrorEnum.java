package com.jd.cho.rule.engine.common.exceptions;

import lombok.AllArgsConstructor;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@AllArgsConstructor
public enum BizErrorEnum implements CommonException {

    NO_PERMISSION(-1, "无权限操作"),
    SYSTEM_ERROR(-2, "系统异常"),
    DOES_NOT_EXIST(-3, "数据不存在"),


    DATA_HAS_CHANGE(-5, "数据已经被修改"),
    CODE_IS_EXIST(-6, "该code已存在，请修改后重试"),


    ;

    /**
     * 错误码
     */
    private final Integer errorCode;
    /**
     * 错误信息
     */
    private final String errorMsg;


    @Override
    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
