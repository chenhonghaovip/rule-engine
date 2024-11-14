package com.jd.cho.rule.engine.spi;

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


    /**
     * 获取被增强后的因子的值
     *
     * @param factorCode 增强后因子code
     * @param value
     * @return 因子值
     */
    Object getFactorValue(String factorCode, Object value);


}
