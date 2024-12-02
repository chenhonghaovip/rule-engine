package com.jd.cho.rule.engine.method.mock;

import com.jd.cho.rule.engine.domain.model.RuleFactor;

import java.util.Date;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public interface SignDomainService {

    String getSign();


    String getSignInfo(String name, Date date, RuleFactor ruleFactor);


    RuleFactor getRuleFactor();
}
