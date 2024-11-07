package com.jd.cho.rule.engine.domain.model;

import lombok.Data;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class RuleDef {
    /**
     * 主键
     */
    private Long id;

    /**
     * 规则编码
     */
    private String ruleCode;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 规则条件
     */
    private String ruleCondition;

    /**
     * 规则行为
     */
    private String ruleAction;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 是否最新版本（0:否，1:是）
     */
    private Integer latest;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态（0:否，1:是）
     */
    private Integer status;

}
