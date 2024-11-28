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
     * @param rulePackCode 规则包code
     * @param context      上下文参数
     * @return 规则执行结果:(成功、失败)  规则执行动作结果:context.get("result")
     */
    boolean execute(String rulePackCode, Map<String, Object> context);

}
