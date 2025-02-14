package com.jd.cho.rule.engine.spi;


import com.jd.cho.rule.engine.core.executer.set.group.RuleDefExecutor;
import com.jd.cho.rule.engine.domain.model.RuleDef;

import java.util.List;
import java.util.Map;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public interface RuleDefsExecutor {

    /**
     * 获取规则组编码
     *
     * @return 规则组编码
     */
    String getCode();

    /**
     * 获取规则组编码
     *
     * @return 规则组编码
     */
    String getName();

    /**
     * 执行规则组
     *
     * @param ruleDefExecutor 规则执行器
     * @param list            规则定义信息
     * @param context         规则上下文
     * @return 是否执行成功
     */
    boolean execute(RuleDefExecutor ruleDefExecutor, List<RuleDef> list, Map<String, Object> context);
}
