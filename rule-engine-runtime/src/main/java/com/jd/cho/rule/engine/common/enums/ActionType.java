package com.jd.cho.rule.engine.common.enums;

import lombok.Getter;

/**
 * 执行动作类型
 *
 * @author chenhonghao12
 * @version 1.0
 */
@Getter
public enum ActionType {

    /**
     * 赋值操作
     */
    ASSIGN("ASSIGN", "赋值"),

    /**
     * 执行操作
     */
    EXECUTE("EXECUTE", "执行"),

    ;

    private final String code;
    private final String desc;

    ActionType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
