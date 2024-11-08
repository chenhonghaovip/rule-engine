package com.jd.cho.rule.engine.domain.model;

import lombok.Data;

import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class RuleDef {
    /**
     * 主键
     */
    private Long id;

    /**
     * 规则条件
     */
    private RuleCondition ruleCondition;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 规则动作
     */
    private List<RuleAction> ruleActions;


}
