package com.jd.cho.rule.engine.domain.atomic;

import com.jd.cho.rule.engine.spi.LoginUserExtendService;
import org.springframework.stereotype.Service;

import java.util.ServiceLoader;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class AtomicLoginUserComponent {

    public String getLoginUser() {
        ServiceLoader<LoginUserExtendService> load = ServiceLoader.load(LoginUserExtendService.class);
        for (LoginUserExtendService loginUserService : load) {
            return loginUserService.getCurrentUser();
        }
        return "admin";
    }

}
