package com.jd.cho.rule.engine.domain.model;

import com.jd.cho.rule.engine.common.base.CommonDict;
import com.jd.cho.rule.engine.common.enums.ExpressOperationEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table rule_factor
 *
 * @author chenhonghao12
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class RuleFactor {
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
     * 主键
     */
    private Long id;

    /**
     * Database Column Remarks:
     * 因子编码
     */
    private String factorCode;

    /**
     * Database Column Remarks:
     * 因子全编码
     */
    private String factorFullCode;

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
     * 支持的操作符
     */
    private List<ExpressOperationEnum> expressOperationList;

    /**
     * Database Column Remarks:
     * 常量类型（Input:输入，Enum:枚举，Script:脚本）
     */
    private String constantType;

    /**
     * Database Column Remarks:
     * 常量值
     */
    private List<CommonDict> constantValues;

    /**
     * Database Column Remarks:
     * 备注
     */
    private String remark;


}