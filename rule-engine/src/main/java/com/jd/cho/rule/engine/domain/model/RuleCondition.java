package com.jd.cho.rule.engine.domain.model;

import lombok.Data;

import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class RuleCondition {
     /**
     * 逻辑运算
     */
    private String logicOperation;

    /**
     * 比较运算
     */
    private String compareOperation;

    /**
     * 左值表达式
     */
    private BasicVar leftVar;

    /**
     * 右值表达式
     */
    private BasicVar rightVar;

    /**
     * 子规则列表
     */
    private List<RuleCondition> children;

}
