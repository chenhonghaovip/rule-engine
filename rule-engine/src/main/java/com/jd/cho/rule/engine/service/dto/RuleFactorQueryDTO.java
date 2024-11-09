package com.jd.cho.rule.engine.service.dto;

import com.jd.cho.rule.engine.common.base.CommonDict;
import com.jd.cho.rule.engine.common.enums.ExpressOperationEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author chenhonghao12
 */
@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class RuleFactorQueryDTO {
    /**
     * 分组编码
     */
    private String groupCode;

    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 分组下规则因子集合
     */
    private List<RuleFactorBean> ruleFactorBeans;


    @Data
    public static class RuleFactorBean {
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
        private String factorType;

        /**
         * 支持的操作符
         */
        private List<ExpressOperationEnum> expressOperationList;

        /**
         * 常量类型（Input:输入，Enum:枚举，Script:脚本）
         */
        private String constantType;

        /**
         * 常量值
         */
        private List<CommonDict> constantValues;

        /**
         * 备注
         */
        private String remark;

    }


}