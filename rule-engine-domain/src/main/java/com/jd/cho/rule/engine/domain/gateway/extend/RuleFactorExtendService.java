package com.jd.cho.rule.engine.domain.gateway.extend;

import com.jd.cho.rule.engine.domain.model.RuleFactor;

import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public interface RuleFactorExtendService {

    /**
     * 业务方对应的规则因子进行扩展
     *
     * @param ruleFactors 原始规则因子
     * @return 增强后的规则因子
     */
    List<RuleFactor> extendFactors(List<RuleFactor> ruleFactors);
}
