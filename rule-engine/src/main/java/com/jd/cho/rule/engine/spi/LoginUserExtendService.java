package com.jd.cho.rule.engine.spi;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public interface LoginUserExtendService {

    /**
     * 获取当前登录用户信息
     *
     * @return 获取当前登录用户
     */
    String getCurrentUser();
}
