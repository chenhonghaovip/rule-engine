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
    AND("&&", "且"),
    /**
     * 脚本类型
     */
    OR("||", "或"),

    ;

    private final String code;
    private final String desc;

    RelationTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static final List<RelationTypeEnum> RELATION_TYPE_ENUMS = Arrays.asList(RelationTypeEnum.values());

    public static RelationTypeEnum getByCode(String code) {
        return RELATION_TYPE_ENUMS.stream().filter(each -> each.getCode().equals(code)).findFirst().orElse(null);
    }

}
