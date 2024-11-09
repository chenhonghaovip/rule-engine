package com.jd.cho.rule.engine.domain.model;

import com.jd.cho.rule.engine.common.enums.RulePackTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RulePack {

    /**
     * 规则包名称
     */
    private String rulePackName;

    /**
     * 规则包编码
     */
    private String rulePackCode;

    /**
     * 规则包类型
     */
    private RulePackTypeEnum rulePackType;

    /**
     * 规则包编排策略
     */
    private String ruleArrangeStrategy;

    /**
     * 规则包参数
     */
    private String packParams;

    /**
     * 规则包描述
     */
    private String remark;

    /**
     * 规则包规则
     */
    private List<RuleDef> rules;
}
