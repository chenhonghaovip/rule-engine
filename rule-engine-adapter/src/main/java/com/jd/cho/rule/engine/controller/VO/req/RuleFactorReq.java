package com.jd.cho.rule.engine.controller.VO.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class RuleFactorReq implements Serializable {

    private Long id;
    /**
     * 分组编码
     */
    private String groupCode;

    /**
     * 因子编码
     */
    private String factorCode;

    /**
     * 因子名称
     */
    private String factorName;

    /**
     * 因子类型（日期、数值、集合、布尔、文本）
     */
    private String factorType;

    /**
     * 常量类型（Input:输入，Enum:枚举，Script:脚本）
     */
    private String constantType;

    /**
     * 常量值脚本
     */
    private String constantValue;

    /**
     * 取值脚本参数
     */
    private String factorScriptParam;

    /**
     * 取值脚本
     */
    private String factorScript;

    /**
     * 备注
     */
    private String remark;

    /**
     * 拓展信息
     */
    private String extInfo;
}
