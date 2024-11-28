package com.jd.cho.rule.engine.domain.model;

import lombok.Data;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class RuleAction {

    /**
     * @see com.jd.cho.rule.engine.common.enums.ActionType
     * 执行类型
     */
    private String executeType;

    /**
     * 字段code
     */
    private String fieldCode;

    /**
     * 字段值
     */
    private Object values;
}
