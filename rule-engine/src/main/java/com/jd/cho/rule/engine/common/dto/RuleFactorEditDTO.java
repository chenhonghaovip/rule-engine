package com.jd.cho.rule.engine.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 规则因子表
 *
 * @author chenhonghao12
 * @TableName rule_factor
 */
@Data
public class RuleFactorEditDTO implements Serializable {


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


}