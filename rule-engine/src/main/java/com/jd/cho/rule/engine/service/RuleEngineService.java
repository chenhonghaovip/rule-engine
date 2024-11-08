package com.jd.cho.rule.engine.service;


import java.util.Map;

/**
 * 规则执行服务
 *
 * @author chenhonghao12
 * @version 1.0
 */
public interface RuleEngineService {

    /**
     * 执行规则引擎协议
     *
     * @param ruleCode 规则code
     * @param context  上下文
     * @return 执行结果（成功、失败）
     */
    boolean execute(String ruleCode, Map<String, Object> context);


//    /**
//     * 执行规则引擎协议
//     *
//     * @param abstractRuleGroup 规则组
//     * @param context           上下文
//     * @return 执行结果
//     */
//    boolean execute(AbstractRuleGroup abstractRuleGroup, Map<String, Object> context);

}
