package com.jd.cho.rule.engine.config;

import com.jd.cho.rule.engine.domain.model.UserInfo;
import com.jd.cho.rule.engine.spi.LoginUserExtendService;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public class MockLoginUserExtendService implements LoginUserExtendService {
    @Override
    public String getCurrentUser() {
        return "admin";
    }

    @Override
    public UserInfo getCurrentUserInfo() {
        return UserInfo.builder().loginUser("admin").tenant("10000").build();
    }
}
