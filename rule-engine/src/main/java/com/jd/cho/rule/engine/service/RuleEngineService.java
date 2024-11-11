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
     * @param rulePackCode 规则code
     * @param context      上下文
     * @return 执行结果（成功、失败）
     */
    boolean execute(String rulePackCode, Map<String, Object> context);

}
