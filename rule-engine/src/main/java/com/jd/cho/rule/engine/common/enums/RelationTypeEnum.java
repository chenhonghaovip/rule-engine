package com.jd.cho.rule.engine.common.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Getter
public enum RelationTypeEnum {
    /**
     * 且
     */
    AND("AND", "&&", "且"),

    /**
     * 或
     */
    OR("OR", "||", "或"),
    ;

    private final String code;
    private final String expression;
    private final String desc;


    RelationTypeEnum(String code, String expression, String desc) {
        this.code = code;
        this.expression = expression;
        this.desc = desc;
    }

    public static final List<RelationTypeEnum> RELATION_TYPE_ENUMS = Arrays.asList(RelationTypeEnum.values());

    public static RelationTypeEnum getByCode(String code) {
        return RELATION_TYPE_ENUMS.stream().filter(each -> each.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
    }

}
