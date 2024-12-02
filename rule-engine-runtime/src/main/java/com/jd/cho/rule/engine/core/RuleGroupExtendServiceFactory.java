package com.jd.cho.rule.engine.core;

import com.jd.cho.rule.engine.spi.RuleDefsExecutor;

public interface RuleGroupExtendServiceFactory {
    RuleDefsExecutor get(String ruleArrangeStrategy);
}
