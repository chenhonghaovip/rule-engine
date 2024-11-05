package com.jd.cho.rule.engine.client.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 规则因子分组表
 *
 * @TableName rule_factor_group
 */
@Data
public class RuleFactorGroupDTO implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 分组编码
     */
    private String groupCode;

    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 是否删除 1-否，0-是
     */
    private Integer yn;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date modifyTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 租户
     */
    private String tenant;

}