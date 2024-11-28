package com.jd.cho.rule.engine.common.exceptions;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public interface CommonException {

    /**
     * 获取异常code
     *
     * @return code
     */
    Integer getErrorCode();

    /**
     * 获取异常信息
     *
     * @return 异常信息
     */
    String getErrorMsg();
}
