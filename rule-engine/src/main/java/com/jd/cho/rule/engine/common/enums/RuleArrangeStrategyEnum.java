package com.jd.cho.rule.engine.common.enums;

import lombok.Getter;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Getter
public enum RuleArrangeStrategyEnum {

    /**
     * 普通规则
     */
    NORMAL("NORMAL", "普通规则"),

    /**
     * 决策集
     */
    DECISION_SET("DECISION_SET", "决策集"),

    /**
     * 决策表
     */
    DECISION_TABLES("DECISION_TABLES", "决策表"),

    ;

    private final String code;
    private final String desc;

    RuleArrangeStrategyEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
