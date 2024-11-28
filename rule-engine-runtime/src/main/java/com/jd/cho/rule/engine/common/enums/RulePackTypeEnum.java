package com.jd.cho.rule.engine.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RulePackTypeEnum {

    /**
     * 普通规则
     */
    NORMAL("NORMAL", "普通规则", RuleDef.class),

    /**
     * 决策集
     */
    DECISION_SET("DECISION_SET", "决策集", RuleDef.class),

    ;

    private final String code;
    private final String desc;
    private final Class<?> aClass;

    RulePackTypeEnum(String code, String desc, Class<?> aClass) {
        this.code = code;
        this.desc = desc;
        this.aClass = aClass;
    }


    public static final List<RulePackTypeEnum> RULE_PACK_TYPE_ENUMS = Arrays.asList(RulePackTypeEnum.values());

    public static RulePackTypeEnum getByCode(String code) {
        return Arrays.stream(RulePackTypeEnum.values()).filter(each -> each.getCode().equals(code)).findFirst().orElse(null);
    }
}
