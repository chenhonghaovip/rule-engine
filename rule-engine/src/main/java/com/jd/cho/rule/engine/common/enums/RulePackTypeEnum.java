package com.jd.cho.rule.engine.common.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Getter
public enum RulePackTypeEnum {

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

    RulePackTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static final List<RulePackTypeEnum> RULE_PACK_TYPE_ENUMS = Arrays.asList(RulePackTypeEnum.values());

    public static RulePackTypeEnum getByCode(String code) {
        return Arrays.stream(RulePackTypeEnum.values()).filter(each -> each.getCode().equals(code)).findFirst().orElse(null);
    }
}
