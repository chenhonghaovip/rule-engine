package com.jd.cho.rule.engine.common.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Getter
public enum ConstantEnum {
    /**
     * 输入类型
     */
    INPUT("Input", "输入类型"),

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

    public static ConstantEnum getByCode(String code) {
        return Arrays.stream(ConstantEnum.values()).filter(each -> Objects.equals(each.getCode(), code)).findFirst().orElse(null);
    }
}
