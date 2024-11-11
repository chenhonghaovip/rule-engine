package com.jd.cho.rule.engine.spi;

import com.jd.cho.rule.engine.domain.model.UserInfo;

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


    /**
     * 获取当前登录用户信息
     *
     * @return 获取当前登录用户
     */
    UserInfo getCurrentUserInfo();
}
