package com.jd.cho.rule.engine.adapter.dto;

import com.jd.cho.rule.engine.common.base.CommonDict;
import com.jd.cho.rule.engine.common.enums.ConstantEnum;
import com.jd.cho.rule.engine.factor.model.ComparativeOperator;
import com.jd.cho.rule.engine.factor.model.RuleFactorType;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 规则因子表
 *
 * @author chenhonghao12
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
     * 原始因子编码
     */
    private String originalFactorCode;

    /**
     * 因子名称
     */
    private String factorName;

    /**
     * 因子类型（日期、数值、集合、布尔、文本）
     */
    private RuleFactorType factorType;

    /**
     * 因子分组code
     */
    private String groupCode;

    /**
     * 支持的操作符
     */
    private List<ComparativeOperator> expressOperationList;

    /**
     * 常量类型（Input:输入，Enum:枚举，Script:脚本）
     */
    private ConstantEnum constantType;

    /**
     * 常量值脚本
     */
    private String constantValue;

    /**
     * 常量值
     */
    private List<CommonDict> constantValues;

    /**
     * 脚本代码
     */
    private String factorScript;

    /**
     * 脚本参数
     */
    private String factorScriptParam;

    /**
     * 备注
     */
    private String remark;

    /**
     * 拓展信息
     */
    private String extInfo;

}