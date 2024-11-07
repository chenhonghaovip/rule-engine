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
     * 因子code
     */
    private String factorCode;

    /**
     * 原始因子code
     */
    private String originalFactorCode;

    /**
     * 字段值
     */
    private Object value;

    /**
     * 子规则列表
     */
    private List<RuleCondition> children;

}
