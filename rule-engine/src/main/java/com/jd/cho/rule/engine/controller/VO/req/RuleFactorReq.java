package com.jd.cho.rule.engine.controller.VO.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class RuleFactorReq implements Serializable {

    /**
     * Database Column Remarks:
     * 分组编码
     */
    private String groupCode;

    /**
     * 分组名称
     */
    private String groupName;

    /**
     * Database Column Remarks:
     * 因子编码
     */
    private String factorCode;


    /**
     * Database Column Remarks:
     * 因子名称
     */
    private String factorName;

    /**
     * Database Column Remarks:
     * 因子类型（日期、数值、集合、布尔、文本）
     */
    private String factorType;

    /**
     * Database Column Remarks:
     * 常量类型（Input:输入，Enum:枚举，Script:脚本）
     */
    private String constantType;

    /**
     * Database Column Remarks:
     * 常量值
     */
    private String constantValue;


    /**
     * Database Column Remarks:
     * 取值脚本参数
     */
    private String factorScriptParam;

    /**
     * Database Column Remarks:
     * 取值脚本
     */
    private String factorScript;

    /**
     * Database Column Remarks:
     * 备注
     */
    private String remark;
}
