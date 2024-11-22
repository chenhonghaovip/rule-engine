package com.jd.cho.rule.engine.domain.model;

import com.jd.cho.rule.engine.common.enums.VarTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class BasicVar {

    /**
     * @see VarTypeEnum
     * 变量类型（函数、规则因子、常量）
     */
    private String ruleType;

    /**
     * code值
     * 当类型为函数时，为函数code
     * 当类型为规则因子时，为因子code
     */
    private String code;

    /**
     * 原始因子code（仅当类型为规则因子时，存在该值）
     */
    private String originalFactorCode;

    /**
     * 常量值
     * 当类型为常量时，为常量值
     */
    private Object values;

    /**
     * 参数值（仅当类型为方法时，存在该值）
     */
    private List<BasicVar> params;

}
