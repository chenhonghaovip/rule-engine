package com.jd.cho.rule.engine.common.protocol;

import com.google.common.collect.Maps;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import com.jd.cho.rule.engine.service.dto.RulePackDTO;

import java.util.Map;
import java.util.Set;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public abstract class CommonExpressionParser {
    protected static final Map<String, CommonExpressionParser> MAP = Maps.newHashMap();

    /**
     * 校验规则条件
     *
     * @param rulePackDTO 规则包
     */
    abstract void checkRuleConditionInner(RulePackDTO rulePackDTO, Map<String, RuleFactor> ruleFactorMap);

    /**
     * 获取规则因子参数
     *
     * @param rulePackDTO 规则包
     * @return 规则因子参数
     */
    abstract Set<String> getFactorScriptParamInner(RulePackDTO rulePackDTO);

}
