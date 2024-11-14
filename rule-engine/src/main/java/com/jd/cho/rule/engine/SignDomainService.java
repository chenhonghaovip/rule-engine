package com.jd.cho.rule.engine;

import org.springframework.stereotype.Component;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Component
public class SignDomainService {

    public String getSign(String sceneCode, String factorCode) {
        System.out.println("11111111111------11111111----11111111");
        System.out.println("sceneCode=" + sceneCode);
        System.out.println("factorCode=" + factorCode);
        return "1111";
    }

}
