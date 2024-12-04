package com.jd.cho.rule.engine.method.mock;

import com.google.common.collect.Lists;
import com.jd.cho.rule.engine.common.anno.ApiMethod;
import com.jd.cho.rule.engine.common.base.CommonDict;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class SignDomainServiceImpl implements SignDomainService {

    @Resource
    private ApplicationContext applicationContext;

    @Override
    @ApiMethod(code = "getSign", name = "获取签名", valueScript = "")
    public String getSign() {
        System.out.println(SignDomainServiceImpl.class.getName());
        return "12314";
    }

    @Override
    @ApiMethod(code = "getSignInfo", name = "获取签名", valueScript = "")
    public String getSignInfo(String name, Date date, RuleFactor ruleFactor) {
        System.out.println("asfafa");
        return "abc";
    }

    @Override
    @ApiMethod(code = "getRuleFactor", name = "获取签名", valueScript = "")
    public RuleFactor getRuleFactor() {
        RuleFactor ruleFactor = new RuleFactor();
        ruleFactor.setFactorCode("sdfafafa");
        return ruleFactor;
    }

    @Override
    public List<CommonDict> getDict() {
        CommonDict build1 = CommonDict.builder().code(1).desc("1").build();
        CommonDict build2 = CommonDict.builder().code(2).desc("2").build();
        CommonDict build3 = CommonDict.builder().code(3).desc("3").build();

        return Lists.newArrayList(build1, build2, build3);
    }

    @ApiMethod(code = "toString1", name = "toString1", valueScript = "")
    public static String toString1() {
        return "123";
    }
}
