package com.jd.cho.rule.engine.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleFactorType {
    /**
     * 类型编码
     */
    private String code;

    /**
     * 类型描述
     */
    private String desc;

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
