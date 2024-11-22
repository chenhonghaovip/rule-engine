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
public enum VarTypeEnum {

    /**
     * 函数
     */
    FUNCTION("METHOD", "函数"),

    /**
     * 因子
     */
    FACTOR("FACTOR", "规则因子"),

    /**
     * 常量
     */
    CONSTANT("CONSTANT", "常量"),


    ;

    private final String code;
    private final String desc;

    VarTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static final List<VarTypeEnum> RULE_PACK_TYPE_ENUMS = Arrays.asList(VarTypeEnum.values());

    public static VarTypeEnum getByCode(String code) {
        return Arrays.stream(VarTypeEnum.values()).filter(each -> each.getCode().equals(code)).findFirst().orElse(null);
    }
}
