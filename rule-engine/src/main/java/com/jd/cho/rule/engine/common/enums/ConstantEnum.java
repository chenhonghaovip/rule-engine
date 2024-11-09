package com.jd.cho.rule.engine.common.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Getter
public enum ConstantEnum {
    /**
     * 脚本类型
     */
    INPUT("Input", "脚本类型"),

    /**
     * 枚举类型
     */
    ENUM("Enum", "枚举类型"),
    /**
     * 脚本类型
     */
    SCRIPT("Script", "脚本类型"),

    ;

    private final String code;
    private final String desc;


    ConstantEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static final List<ConstantEnum> CONSTANT_ENUMS = Arrays.asList(ConstantEnum.values());
}
