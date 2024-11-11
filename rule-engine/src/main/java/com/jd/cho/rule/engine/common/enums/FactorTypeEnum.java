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
public enum FactorTypeEnum {
    /**
     * 文本类型
     */
    TEXT("text", "文本类型"),

    /**
     * 数值类型
     */
    NUM("num", "数值类型"),
    /**
     * 日期类型
     */
    DATE("date", "日期类型"),


    /**
     * 集合类型
     */
    COLLECTION("collection", "集合类型"),


    /**
     * 布尔类型
     */
    BOOLEAN("boolean", "布尔类型"),
    ;


    private final String code;
    private final String desc;

    FactorTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static final List<FactorTypeEnum> FACTOR_TYPE_ENUMS = Arrays.asList(FactorTypeEnum.values());

    public static FactorTypeEnum getByCode(String code) {
        return Arrays.stream(FactorTypeEnum.values()).filter(each -> Objects.equals(each.getCode(), code)).findFirst().orElse(null);
    }
}
