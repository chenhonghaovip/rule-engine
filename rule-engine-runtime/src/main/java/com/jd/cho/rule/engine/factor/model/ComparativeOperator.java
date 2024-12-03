package com.jd.cho.rule.engine.factor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ComparativeOperator {

    /**
     * 归属规则因子类型
     */
    private String code;

    /**
     * 操作符唯一标识
     */
    private String operator;

    /**
     * 操作符名称
     */
    private String remark;

    /**
     * 表达式
     */
    private String expression;
}
