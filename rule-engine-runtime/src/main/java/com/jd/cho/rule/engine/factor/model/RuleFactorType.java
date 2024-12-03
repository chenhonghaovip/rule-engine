package com.jd.cho.rule.engine.factor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RuleFactorType {
    /**
     * 类型编码
     */
    private String code;

    /**
     * 类型描述
     */
    private String desc;


}
