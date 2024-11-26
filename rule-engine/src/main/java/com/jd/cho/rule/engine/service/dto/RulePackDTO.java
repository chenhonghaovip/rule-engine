package com.jd.cho.rule.engine.service.dto;

import com.jd.cho.rule.engine.common.enums.RulePackTypeEnum;
import com.jd.cho.rule.engine.domain.model.BasicVar;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class RulePackDTO implements Serializable {

    private Long id;

    /**
     * 规则包名称
     */
    private String rulePackName;

    /**
     * 规则包code
     */
    private String rulePackCode;
    /**
     * 规则包类型
     */
    private RulePackTypeEnum rulePackType;

    /**
     * 包内规则执行策略
     */
    private String ruleArrangeStrategy;
    /**
     * 规则包描述
     */
    private String remark;

    /**
     * 包内规则信息
     */
    private String ruleContent;

    /**
     * 包内规则信息
     */
    private List<RulesBean> rules;

    @Data
    public static class RulesBean implements Serializable {

        /**
         * 规则条件
         */
        private RuleConditionBean ruleCondition;

        /**
         * 规则优先级
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
             */
            private String logicOperation;

            /**
             * 比较运算
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
}
