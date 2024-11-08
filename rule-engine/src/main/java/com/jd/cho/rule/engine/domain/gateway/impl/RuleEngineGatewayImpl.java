package com.jd.cho.rule.engine.domain.gateway.impl;

import com.google.common.collect.Maps;
import com.jd.cho.rule.engine.common.dict.FactDict;
import com.jd.cho.rule.engine.common.enums.ExpressOperationEnum;
import com.jd.cho.rule.engine.common.util.QlExpressUtil;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.gateway.RuleEngineGateway;
import com.jd.cho.rule.engine.domain.model.RuleAction;
import com.jd.cho.rule.engine.domain.model.RuleCondition;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import com.jd.cho.rule.engine.domain.model.RulePack;
import com.jd.cho.rule.engine.group.RuleGroupRunStrategy;
import com.jd.cho.rule.engine.spi.RuleGroupExtendService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Slf4j
@Service
public class RuleEngineGatewayImpl implements RuleEngineGateway {
    public static final String ORIGINAL_VALUE = "originalValue";

    @Resource
    private RuleConfigGateway ruleConfigGateway;


    @Override
    public boolean execute(RuleDef ruleDef, Map<String, Object> context) {
        Map<String, Object> rightValues = Maps.newHashMap();
        Map<String, String> fieldMapping = Maps.newHashMap();
        String statement = this.buildWhenExpression(ruleDef.getRuleCondition(), rightValues, fieldMapping);
        if (this.executeCondition(statement, context, rightValues, fieldMapping)) {
            this.executeAction(ruleDef.getRuleActions(), context);
            return true;
        }
        return false;
    }

    @Override
    public boolean execute(RulePack rulePack, Map<String, Object> context) {
        List<RuleDef> rules = rulePack.getRules();
        RuleGroupExtendService ruleGroup = RuleGroupRunStrategy.getRuleGroup(rulePack.getRuleArrangeStrategy());
        return ruleGroup.execute(rules, context);
    }

    @Override
    public boolean execute(String rulePackCode, Map<String, Object> context) {
        RulePack rulePack = ruleConfigGateway.rulePackInfo(rulePackCode);
        if (Objects.isNull(rulePack)) {
            return false;
        }
        return this.execute(rulePack, context);
    }


    private boolean executeCondition(String expression, Map<String, Object> context, Map<String, Object> rightValues, Map<String, String> fieldMapping) {
        return (Boolean) QlExpressUtil.execute(expression, context, rightValues, fieldMapping);
    }


    /**
     * 构建当表达式
     * 该方法用于根据逻辑表达式对象构建相应的当表达式（用于规则引擎等场景）
     * 当表达式可以是简单的比较表达式，也可以是复合的逻辑表达式
     *
     * @param ruleCondition 逻辑表达式对象，包含构建当表达式所需的信息
     * @return 返回构建好的当表达式字符串
     */
    public String buildWhenExpression(RuleCondition ruleCondition, Map<String, Object> rightValues, Map<String, String> fieldMapping) {
        if (Objects.isNull(ruleCondition)) {
            return "";
        }

        StringBuilder mvelExpression = new StringBuilder();
        String type = StringUtils.isNotBlank(ruleCondition.getLogicOperation()) ? FactDict.RELATION_TYPE : FactDict.EXPRESSION_TYPE;

        switch (type) {
            case FactDict.EXPRESSION_TYPE:
                String factorCode = ruleCondition.getFactorCode();
                String originalFactorCode = ruleCondition.getOriginalFactorCode();
                if (!Objects.equals(originalFactorCode, factorCode)) {
                    fieldMapping.put(factorCode, originalFactorCode);
                }
                Object fieldValue = ruleCondition.getValue();
                String compareOperation = ruleCondition.getCompareOperation();
                mvelExpression.append(buildOperatorExpress(compareOperation, factorCode, fieldValue, rightValues));
                break;
            case FactDict.RELATION_TYPE:
                List<RuleCondition> children = ruleCondition.getChildren();
                if (CollectionUtils.isEmpty(children)) {
                    return FactDict.SYMBOL_EMPTY;
                }
                String logicOperator = this.convertRelationExpress(ruleCondition.getLogicOperation());
                StringBuilder childrenExpression = new StringBuilder();
                for (RuleCondition child : children) {
                    // 递归构建单个规则条件
                    String childExpression = buildWhenExpression(child, rightValues, fieldMapping);
                    if (!childExpression.isEmpty()) {
                        if (childrenExpression.length() > 0) {
                            childrenExpression.append(FactDict.SYMBOL_SPACE).append(logicOperator).append(FactDict.SYMBOL_SPACE);
                        }
                        childrenExpression.append(FactDict.LEFT_BRACKETS).append(childExpression).append(FactDict.RIGHT_BRACKETS);
                    }
                }
                mvelExpression.append(childrenExpression);
                break;
            default:
                break;
        }
        return mvelExpression.toString();
    }

    /**
     * 转换条件连接符
     *
     * @param relation 条件连接符
     */
    protected String convertRelationExpress(String relation) {
        if (StringUtils.isEmpty(relation)) {
            return FactDict.SYMBOL_EMPTY;
        } else if (relation.equalsIgnoreCase(FactDict.RELATION_AND)) {
            return FactDict.LOGICAL_AND;
        } else if (relation.equalsIgnoreCase(FactDict.RELATION_OR)) {
            return FactDict.LOGICAL_OR;
        }
        return relation;
    }

    public String buildOperatorExpress(String operator, String fieldName, Object value, Map<String, Object> rightValues) {
        ExpressOperationEnum operation = ExpressOperationEnum.getOperationByOperator(operator);
        if (Objects.isNull(operation)) {
            return FactDict.SYMBOL_EMPTY;
        }
        if (Objects.nonNull(value)) {
            rightValues.put(buildValueExpress(ORIGINAL_VALUE, fieldName), value);
        }
        String expression = operation.getExpression();
        return String.format(expression, fieldName, buildValueExpress(ORIGINAL_VALUE, fieldName));
    }

    /**
     * 构建mvel取值表达式
     *
     * @param fieldName 字段名称
     */
    private static String buildValueExpress(String entityName, String fieldName) {
        return String.format("%s_%s", entityName, fieldName);
    }

    public void executeAction(List<RuleAction> ruleActionBeans, Map<String, Object> context) {
//        List<RuleAction> ruleActionBeans = JSON.parseArray(actionExpression, RuleAction.class);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Map maps = new HashMap();");

        ruleActionBeans.forEach(each -> {
            Object values = each.getValues();
            if (values instanceof String) {
                values = "\"" + values + "\"";
            }
            stringBuilder.append("maps.put(\"").append(each.getFieldCode()).append("\",").append(values).append(");");
        });
        stringBuilder.append("return maps;");
        Object execute = QlExpressUtil.execute(stringBuilder.toString(), context);
        if (Objects.nonNull(execute)) {
            Map<String, Object> executeMaps = (Map<String, Object>) execute;
            Object result = context.get(FactDict.RESULT_ALIAS);
            Map result1;
            if (Objects.nonNull(result)) {
                result1 = (Map) result;
            } else {
                result1 = Maps.newHashMap();
                context.put(FactDict.RESULT_ALIAS, result1);
            }
            result1.putAll(executeMaps);
        }
    }


}
