package com.jd.cho.rule.engine.domain;

import org.springframework.stereotype.Service;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class SignDomainServiceImpl implements SignDomainService {
    @Override
    public String getSign() {
        System.out.println(SignDomainServiceImpl.class.getName());
        return "12314";
    }
}
