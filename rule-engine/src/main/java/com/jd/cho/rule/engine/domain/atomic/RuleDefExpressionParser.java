package com.jd.cho.rule.engine.domain.atomic;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.jd.cho.rule.engine.common.dict.Dict;
import com.jd.cho.rule.engine.common.enums.ExpressOperationEnum;
import com.jd.cho.rule.engine.common.enums.FactorTypeEnum;
import com.jd.cho.rule.engine.common.enums.RelationTypeEnum;
import com.jd.cho.rule.engine.common.exceptions.BizErrorEnum;
import com.jd.cho.rule.engine.common.exceptions.BusinessException;
import com.jd.cho.rule.engine.common.util.AssertUtil;
import com.jd.cho.rule.engine.domain.model.RuleCondition;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

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


    private static final String ORIGINAL_VALUE = "original";


    public static void checkRuleCondition(RuleCondition ruleCondition, Map<String, RuleFactor> ruleFactorMap) {
        try {
            checkRuleConditionInner(ruleCondition, ruleFactorMap);
        } catch (BusinessException e) {
            log.error("规则定义不合法:code:{},msg:{},param:{}", e.getErrorCode(), e.getMessage(), JSON.toJSONString(ruleCondition));
            throw new BusinessException(e);
        } catch (Exception e) {
            log.error("规则定义不合法，请检查后重试:{}", JSON.toJSONString(ruleCondition), e);
            throw new BusinessException(e);
        }

    }

    /**
     * 校验规则是否合法
     *
     * @param ruleCondition 规则协议
     * @param ruleFactorMap 规则因子信息
     */
    public static void checkRuleConditionInner(RuleCondition ruleCondition, Map<String, RuleFactor> ruleFactorMap) {
        if (Objects.nonNull(ruleCondition)) {
            if (StringUtils.isNotBlank(ruleCondition.getLogicOperation())) {
                AssertUtil.isNotNull(RelationTypeEnum.getByCode(ruleCondition.getLogicOperation()), BizErrorEnum.EXPRESS_OPERATION_DOES_NOT_EXIST);
                if (CollectionUtils.isNotEmpty(ruleCondition.getChildren())) {
                    ruleCondition.getChildren().forEach(each -> checkRuleConditionInner(each, ruleFactorMap));
                }
            } else {
                // 判断规则是否异常
                AssertUtil.isNotBlank(ruleCondition.getOriginalFactorCode(), BizErrorEnum.ORIGINAL_FACTOR_CODE_IS_NOT_EXIST);
                AssertUtil.isNotBlank(ruleCondition.getFactorCode(), BizErrorEnum.FACTOR_CODE_IS_NOT_EXIST);
                RuleFactor ruleFactor = ruleFactorMap.get(ruleCondition.getOriginalFactorCode());
                AssertUtil.isNotNull(ruleFactor, BizErrorEnum.RULE_FACTOR_DOES_NOT_EXIST);

                FactorTypeEnum factorType = ruleFactor.getFactorType();
                AssertUtil.isNotNull(factorType, BizErrorEnum.FACTOR_TYPE_IS_NOT_EXIST);

                ExpressOperationEnum byCode = ExpressOperationEnum.getByCode(ruleCondition.getCompareOperation());
                AssertUtil.isTrue(byCode.getGroup().equals(factorType.getCode()), BizErrorEnum.FACTOR_TYPE_AND_OPERATE_NOT_MATCH);
            }
        }


    }

    public static void main(String[] args) {
        String s1 = "{\n" + "    \"children\":\n" + "    [\n" + "        {\n" + "            \"children\":\n" + "            [\n" + "                {\n" + "                    \"compareOperation\": \"TEXT_NULL\",\n" + "                    \"factorCode\": \"c\",\n" + "                    \"originalFactorCode\": \"c_c\",\n" + "                    \"value\": \"20\"\n" + "                },\n" + "                {\n" + "                    \"compareOperation\": \"DATE_IS_NULL\",\n" + "                    \"factorCode\": \"d\",\n" + "                    \"originalFactorCode\": \"d\",\n" + "                    \"value\": \"null\"\n" + "                }\n" + "            ],\n" + "            \"logicOperation\": \"or\"\n" + "        },\n" + "        {\n" + "            \"compareOperation\": \"COLLECTION_CONTAIN_ANY_ONE\",\n" + "            \"factorCode\": \"a\",\n" + "            \"originalFactorCode\": \"a\",\n" + "            \"value\":\n" + "            [\n" + "                \"11\",\n" + "                \"23\"\n" + "            ]\n" + "        },\n" + "        {\n" + "            \"compareOperation\": \"DATE_AFTER\",\n" + "            \"factorCode\": \"b\",\n" + "            \"originalFactorCode\": \"b\",\n" + "            \"value\": \"2021-03-11 11:11:11\"\n" + "        }\n" + "    ],\n" + "    \"logicOperation\": \"and\"\n" + "}";

        RuleCondition ruleCondition = JSON.parseObject(s1, RuleCondition.class);

        checkRuleCondition(ruleCondition, Maps.newHashMap());
        String s = buildWhenExpression(ruleCondition, Maps.newHashMap(), Maps.newHashMap());
        System.out.println(s);
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
            return Dict.SYMBOL_EMPTY;
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

                RelationTypeEnum relationTypeEnum = RelationTypeEnum.getByCode(ruleCondition.getLogicOperation());
                AssertUtil.isNotNull(relationTypeEnum);

                StringBuilder currentExpression = new StringBuilder();
                for (RuleCondition child : children) {
                    // 递归构建单个规则条件
                    String childExpression = buildWhenExpression(child, rightValues, fieldMapping);
                    if (!childExpression.isEmpty()) {
                        if (currentExpression.length() > 0) {
                            currentExpression.append(Dict.SYMBOL_SPACE).append(relationTypeEnum.getExpression()).append(Dict.SYMBOL_SPACE);
                        }
                        currentExpression.append(Dict.LEFT_BRACKETS).append(childExpression).append(Dict.RIGHT_BRACKETS);
                    }
                }
                mvelExpression.append(currentExpression);
                break;
            default:
                break;
        }
        return mvelExpression.toString();
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
        AssertUtil.isNotNull(operation);

        String originalFieldName = buildValueExpress(fieldName);
        if (Objects.nonNull(value)) {
            rightValues.put(originalFieldName, value);
        }
        String expression = operation.getExpression();
        return String.format(expression, fieldName, originalFieldName);
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
