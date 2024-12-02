package com.jd.cho.rule.engine.core;

import com.jd.cho.rule.engine.spi.RuleGroupExtendService;

public interface RuleGroupExtendServiceFactory {
    RuleGroupExtendService get(String ruleArrangeStrategy);
}
