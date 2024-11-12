package com.jd.cho.rule.engine.domain.atomic;

import com.jd.cho.rule.engine.dal.DO.BaseEntity;
import com.jd.cho.rule.engine.domain.model.UserInfo;
import com.jd.cho.rule.engine.spi.LoginUserExtendService;

import java.util.Date;
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


    public static <T extends BaseEntity> void packCreateBaseInfo(T t) {
        UserInfo userInfo = getLoginUserInfo();
        t.setTenant(userInfo.getTenant());
        t.setCreator(userInfo.getLoginUser());
        t.setCreateTime(new Date());
        t.setYn(true);
    }

    public static <T extends BaseEntity> void packUpdateBaseInfo(T t) {
        UserInfo userInfo = getLoginUserInfo();
        t.setModifier(userInfo.getLoginUser());
        t.setModifyTime(new Date());
    }


}
