package com.jd.cho.rule.engine.domain.gateway;

import com.jd.cho.rule.engine.domain.model.RuleDef;

import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public interface RuleDomainGateway {

    /**
     * 查询规则定义
     *
     * @param ruleCodes 规则编码
     * @return 规则定义信息
     */
    List<RuleDef> ruleDefQuery(List<String> ruleCodes);



}
