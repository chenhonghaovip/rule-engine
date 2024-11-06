package com.jd.cho.rule.engine.client.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class RuleDefDTO implements Serializable {


    @JSONField(name = "ruleName")
    private String ruleName;
    @JSONField(name = "ruleCode")
    private String ruleCode;
    @JSONField(name = "ruleCondition")
    private RuleConditionBean ruleCondition;
    @JSONField(name = "priority")
    private Integer priority;
    @JSONField(name = "remark")
    private String remark;
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
         * 字段code
         */
        @JSONField(name = "fieldCode")
        private String fieldCode;

        /**
         * 字段类型
         */
        private String fieldType;

        /**
         * 字段值
         */
        @JSONField(name = "value")
        private Object value;


        /**
         * 子规则列表
         */
        @JSONField(name = "children")
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
