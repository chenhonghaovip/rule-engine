package com.jd.cho.rule.engine.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RuleTypeEnum {

    /**
     * 函数
     */
    FUNCTION("FUNCTION", "函数"),

    /**
     * 因子
     */
    FACTOR("FACTOR", "规则因子"),


    ;

    private final String code;
    private final String desc;

    RuleTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static final List<RuleTypeEnum> RULE_PACK_TYPE_ENUMS = Arrays.asList(RuleTypeEnum.values());

    public static RuleTypeEnum getByCode(String code) {
        return Arrays.stream(RuleTypeEnum.values()).filter(each -> each.getCode().equals(code)).findFirst().orElse(null);
    }
}
