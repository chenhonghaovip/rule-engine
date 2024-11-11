package com.jd.cho.rule.engine.controller.VO.req;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class RulePackReq implements Serializable {

    private Long id;

    @JSONField(name = "rulePackName")
    private String rulePackName;
    @JSONField(name = "rulePackCode")
    private String rulePackCode;
    @JSONField(name = "rulePackType")
    private String rulePackType;
    @JSONField(name = "ruleArrangeStrategy")
    private String ruleArrangeStrategy;
    @JSONField(name = "remark")
    private String remark;
    @JSONField(name = "rules")
    private List<RulesBean> rules;

    @Data
    public static class RulesBean implements Serializable {

        @JSONField(name = "ruleCondition")
        private RuleConditionBean ruleCondition;
        @JSONField(name = "priority")
        private Integer priority;
        @JSONField(name = "ruleAction")
        private List<RuleActionBean> ruleAction;

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
             * 因子code
             */
            private String factorCode;

            /**
             * 原始因子code
             */
            private String originalFactorCode;

            /**
             * 字段值
             */
            private Object value;


            /**
             * 子规则列表
             */
            private List<RuleConditionBean> children;
        }

        @Data
        public static class RuleActionBean implements Serializable {
            /**
             * fieldCode : field1
             * values : [11,22]
             */

            @JSONField(name = "fieldCode")
            private String fieldCode;
            @JSONField(name = "values")
            private Object values;
        }
    }
}
