package com.jd.cho.rule.engine.config;

import com.jd.cho.rule.engine.domain.model.RuleFactor;
import com.jd.cho.rule.engine.spi.RuleFactorExtendService;

import java.util.List;
import java.util.Map;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public class MockRuleFactorExtendService implements RuleFactorExtendService {

    @Override
    public List<RuleFactor> extendFactors(List<RuleFactor> ruleFactors, Map<String, Object> context) {

        return ruleFactors;
    }

}
