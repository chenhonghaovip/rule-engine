package com.jd.cho.rule.engine.domain.atomic;

import com.jd.cho.rule.engine.spi.LoginUserService;
import org.springframework.stereotype.Service;

import java.util.ServiceLoader;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class AtomicLoginUserComponent {

    public String getLoginUser() {
        ServiceLoader<LoginUserService> load = ServiceLoader.load(LoginUserService.class);
        for (LoginUserService loginUserService : load) {
            return loginUserService.getCurrentUser();
        }
        return "admin";
    }

}
