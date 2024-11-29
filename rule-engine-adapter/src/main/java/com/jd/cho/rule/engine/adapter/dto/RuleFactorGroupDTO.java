package com.jd.cho.rule.engine.adapter.dto;


import lombok.Data;

import java.io.Serializable;

/**
 * 规则因子分组表
 *
 * @author chenhonghao12
 * @TableName rule_factor_group
 */
@Data
public class RuleFactorGroupDTO implements Serializable {

    /**
     * 分组编码
     */
    private String groupCode;

    /**
     * 分组名称
     */
    private String groupName;


}