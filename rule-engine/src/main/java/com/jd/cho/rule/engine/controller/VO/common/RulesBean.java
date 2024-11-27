package com.jd.cho.rule.engine.controller.VO.common;

import com.jd.cho.rule.engine.domain.model.BasicVar;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class RulesBean {

    /**
     * 规则条件
     */
    private RuleConditionBean ruleCondition;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 规则动作
     */
    private List<RuleActionBean> ruleActions;

    @Data
    public static class RuleConditionBean implements Serializable {

        /**
         * 逻辑运算
         *
         * @see com.jd.cho.rule.engine.common.enums.RelationTypeEnum
         */
        private String logicOperation;

        /**
         * 比较运算
         *
         * @see com.jd.cho.rule.engine.common.enums.ExpressOperationEnum
         */
        private String compareOperation;

        /**
         * 左值表达式
         */
        private BasicVar leftVar;

        /**
         * 右值表达式
         */
        private BasicVar rightVar;

        /**
         * 子规则列表
         */
        private List<RuleConditionBean> children;
    }

    @Data
    public static class RuleActionBean implements Serializable {

        /**
         * 字段code
         */
        private String fieldCode;

        /**
         * 字段值
         */
        private Object values;
    }
}
