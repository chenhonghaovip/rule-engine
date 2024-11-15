package com.jd.cho.rule.engine.domain.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Map;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
@SuperBuilder
public class RuleEngineContext {
    /**
     * 规则表达式
     */
    private String expression;
    /**
     * 上下文信息
     */
    private Map<String, Object> context;

    /**
     * 表达式右值设置
     */
    private Map<String, Object> rightValues;

    /**
     * 表达式中因子映射关系
     */
    private Map<String, String> fieldMapping;

    /**
     * 规则因子集合
     */
    private Map<String, String> factorMap;
}
