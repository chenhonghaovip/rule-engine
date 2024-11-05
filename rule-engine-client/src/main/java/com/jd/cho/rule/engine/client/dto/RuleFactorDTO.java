package com.jd.cho.rule.engine.client.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 规则因子表
 *
 * @TableName rule_factor
 */
@Data
public class RuleFactorDTO implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 因子编码
     */
    private String factorCode;

    /**
     * 因子全编码
     */
    private String factorFullCode;

    /**
     * 因子名称
     */
    private String factorName;

    /**
     * 分组编码
     */
    private String groupCode;

    /**
     * 因子类型（日期、数值、集合、布尔、文本）
     */
    private String factorType;

    /**
     * 常量类型（Input:输入，Enum:枚举，Script:脚本）
     */
    private String constantType;

    /**
     * 常量值
     */
    private String constantValue;

    /**
     * 脚本参数
     */
    private String factorScriptParam;

    /**
     * 脚本代码
     */
    private String factorScript;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态（0:否，1:是）
     */
    private Integer status;

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