package com.jd.cho.rule.engine.domain.gateway;

import com.jd.cho.rule.engine.domain.model.RuleDef;
import com.jd.cho.rule.engine.domain.model.RulePack;

import java.util.Map;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public interface RuleEngineGateway {

    /**
     * 执行规则引擎协议
     *
     * @param ruleDef 规则明细
     * @param context 上下文
     * @return 执行结果
     */
    boolean execute(RuleDef ruleDef, Map<String, Object> context);

    /**
     * 执行规则引擎协议
     *
     * @param rulePack 规则包
     * @param context  上下文
     * @return 执行结果
     */
    boolean execute(RulePack rulePack, Map<String, Object> context);


    /**
     * 执行规则引擎协议
     *
     * @param rulePackCode 规则包code
     * @param context      上下文
     * @return 执行结果（成功、失败）
     */
    boolean execute(String rulePackCode, Map<String, Object> context);

}
