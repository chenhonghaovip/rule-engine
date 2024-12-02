package com.jd.cho.rule.engine.core;

import com.jd.cho.rule.engine.domain.model.RulePack;

import java.util.Map;

public interface AcceptableRulePackExecutor {
    /**
     * 是否可以执行该
     *
     * @param rulePack
     * @return
     */
    boolean accept(RulePack rulePack);

    /**
     * 执行规则引擎协议
     *
     * @param rulePack 规则
     * @param context  上下文
     * @return 执行结果
     */
    boolean execute(RulePack rulePack, Map<String, Object> context);
}
