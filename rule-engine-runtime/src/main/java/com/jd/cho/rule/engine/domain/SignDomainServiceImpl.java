package com.jd.cho.rule.engine.domain;

import com.jd.cho.rule.engine.common.anno.ApiMethod;
import com.jd.cho.rule.engine.common.enums.FactorTypeEnum;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class SignDomainServiceImpl implements SignDomainService {

    @Resource
    private ApplicationContext applicationContext;

    @Override
    @ApiMethod(code = "getSign", name = "获取签名", returnType = FactorTypeEnum.TEXT, values = "")
    public String getSign() {
        System.out.println(SignDomainServiceImpl.class.getName());
        return "12314";
    }

    @Override
    @ApiMethod(code = "getSignInfo", name = "获取签名", returnType = FactorTypeEnum.TEXT, values = "")
    public String getSignInfo(String name, Date date, RuleFactor ruleFactor) {
        System.out.println("asfafa");
        return "abc";
    }

    @Override
    @ApiMethod(code = "getRuleFactor", name = "获取签名", returnType = FactorTypeEnum.TEXT, values = "")
    public RuleFactor getRuleFactor() {
        RuleFactor ruleFactor = new RuleFactor();
        ruleFactor.setFactorCode("sdfafafa");
        return ruleFactor;
    }
}
