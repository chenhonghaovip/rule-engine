package com.jd.cho.rule.engine.domain.atomic;

import com.jd.cho.rule.engine.common.dict.Dict;
import com.jd.cho.rule.engine.common.enums.ExpressOperationEnum;
import com.jd.cho.rule.engine.common.enums.RelationTypeEnum;
import com.jd.cho.rule.engine.common.exceptions.BizErrorEnum;
import com.jd.cho.rule.engine.common.util.AssertUtil;
import com.jd.cho.rule.engine.domain.model.RuleCondition;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 规则协议解析器
 *
 * @author chenhonghao12
 * @version 1.0
 */
@Slf4j
public class RuleDefExpressionParser {


    private static final String ORIGINAL_VALUE = "";

    public static boolean checkRuleCondition(RuleCondition ruleCondition, Map<String, RuleFactor> ruleFactorMap) {
        try {
            if (Objects.nonNull(ruleCondition)) {
                if (StringUtils.isNotBlank(ruleCondition.getLogicOperation())) {
                    log.error("当前逻辑操作符不合法:{}", ruleCondition.getLogicOperation());
                    AssertUtil.isNotNull(RelationTypeEnum.getByCode(ruleCondition.getLogicOperation()), BizErrorEnum.DOES_NOT_EXIST);
                } else {
                    // 判断规则是否异常
                    RuleFactor ruleFactor = ruleFactorMap.get(ruleCondition.getFactorCode());
                    AssertUtil.isNotNull(ruleFactor, BizErrorEnum.DOES_NOT_EXIST);
                    AssertUtil.isNotNull(ruleFactor.getFactorType(), BizErrorEnum.FACTOR_TYPE_IS_NOT_EXIST);

                    ExpressOperationEnum byCode = ExpressOperationEnum.getByCode(ruleCondition.getCompareOperation());
                    AssertUtil.isNotNull(ruleFactor.getFactorType(), BizErrorEnum.FACTOR_TYPE_IS_NOT_EXIST);
                    AssertUtil.isTrue(byCode.getGroup().equals(ruleFactor.getFactorType().getCode()), BizErrorEnum.DOES_NOT_EXIST);

                }
            }
        } catch (Exception e) {
            log.error("规则定义不合法，请检查后重试");
            return false;
        }
        return true;

    }


    /**
     * 构建当表达式
     * 该方法用于根据逻辑表达式对象构建相应的当表达式（用于规则引擎等场景）
     * 当表达式可以是简单的比较表达式，也可以是复合的逻辑表达式
     *
     * @param ruleCondition 逻辑表达式对象，包含构建当表达式所需的信息
     * @return 返回构建好的当表达式字符串
     */
    public static String buildWhenExpression(RuleCondition ruleCondition, Map<String, Object> rightValues, Map<String, String> fieldMapping) {
        if (Objects.isNull(ruleCondition)) {
            return "";
        }

        StringBuilder mvelExpression = new StringBuilder();
        String type = StringUtils.isNotBlank(ruleCondition.getLogicOperation()) ? Dict.RELATION_TYPE : Dict.EXPRESSION_TYPE;

        switch (type) {
            case Dict.EXPRESSION_TYPE:
                String factorCode = ruleCondition.getFactorCode();
                String originalFactorCode = ruleCondition.getOriginalFactorCode();
                if (!Objects.equals(originalFactorCode, factorCode)) {
                    fieldMapping.put(factorCode, originalFactorCode);
                }
                Object fieldValue = ruleCondition.getValue();
                String compareOperation = ruleCondition.getCompareOperation();
                mvelExpression.append(buildOperatorExpress(compareOperation, factorCode, fieldValue, rightValues));
                break;
            case Dict.RELATION_TYPE:
                List<RuleCondition> children = ruleCondition.getChildren();
                if (CollectionUtils.isEmpty(children)) {
                    return Dict.SYMBOL_EMPTY;
                }
                String logicOperator = convertRelationExpress(ruleCondition.getLogicOperation());
                StringBuilder childrenExpression = new StringBuilder();
                for (RuleCondition child : children) {
                    // 递归构建单个规则条件
                    String childExpression = buildWhenExpression(child, rightValues, fieldMapping);
                    if (!childExpression.isEmpty()) {
                        if (childrenExpression.length() > 0) {
                            childrenExpression.append(Dict.SYMBOL_SPACE).append(logicOperator).append(Dict.SYMBOL_SPACE);
                        }
                        childrenExpression.append(Dict.LEFT_BRACKETS).append(childExpression).append(Dict.RIGHT_BRACKETS);
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
    protected static String convertRelationExpress(String relation) {
        if (StringUtils.isEmpty(relation)) {
            return Dict.SYMBOL_EMPTY;
        } else if (relation.equalsIgnoreCase(Dict.RELATION_AND)) {
            return Dict.LOGICAL_AND;
        } else if (relation.equalsIgnoreCase(Dict.RELATION_OR)) {
            return Dict.LOGICAL_OR;
        }
        return relation;
    }

    /**
     * 构建QLExpress脚本
     *
     * @param operator    操作符
     * @param fieldName   字段名称
     * @param value       字段值
     * @param rightValues 右侧参数
     * @return 构建好的表达式
     */
    public static String buildOperatorExpress(String operator, String fieldName, Object value, Map<String, Object> rightValues) {
        ExpressOperationEnum operation = ExpressOperationEnum.getByCode(operator);
        if (Objects.isNull(operation)) {
            return Dict.SYMBOL_EMPTY;
        }
        if (Objects.nonNull(value)) {
            rightValues.put(buildValueExpress(fieldName), value);
        }
        String expression = operation.getExpression();
        return String.format(expression, fieldName, buildValueExpress(fieldName));
    }

    /**
     * 构建mvel取值表达式
     *
     * @param fieldName 字段名称
     */
    private static String buildValueExpress(String fieldName) {
        return String.format("%s_%s", ORIGINAL_VALUE, fieldName);
    }

}
