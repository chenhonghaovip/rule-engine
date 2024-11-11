package com.jd.cho.rule.engine.domain.atomic;

import com.jd.cho.rule.engine.domain.model.UserInfo;
import com.jd.cho.rule.engine.spi.LoginUserExtendService;

import java.util.Objects;
import java.util.ServiceLoader;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public class AtomicLoginUserComponent {

    public static String getLoginUser() {
        ServiceLoader<LoginUserExtendService> load = ServiceLoader.load(LoginUserExtendService.class);
        for (LoginUserExtendService loginUserService : load) {
            return loginUserService.getCurrentUser();
        }
        return "admin";
    }

    public static UserInfo getLoginUserInfo() {
        ServiceLoader<LoginUserExtendService> load = ServiceLoader.load(LoginUserExtendService.class);
        for (LoginUserExtendService loginUserService : load) {
            UserInfo currentUserInfo = loginUserService.getCurrentUserInfo();
            if (Objects.nonNull(currentUserInfo)) {
                return currentUserInfo;
            }
        }
        return UserInfo.builder().loginUser("admin").tenant("10000").build();
    }


}
