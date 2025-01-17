package com.jd.cho.rule.engine.core.executer.set.group;

import com.jd.cho.rule.engine.domain.model.RuleDef;

import java.util.Map;

/**
 * @author chenhonghao12
 */
public interface RuleDefExecutor {
    /**
     * 执行规则引擎协议
     *
     * @param ruleDef 规则明细
     * @param context 上下文
     * @return 执行结果
     */
    boolean execute(RuleDef ruleDef, Map<String, Object> context);
}
