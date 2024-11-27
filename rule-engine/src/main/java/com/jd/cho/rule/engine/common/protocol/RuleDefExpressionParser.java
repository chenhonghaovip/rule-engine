package com.jd.cho.rule.engine.common.protocol;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import com.jd.cho.rule.engine.common.dict.Dict;
import com.jd.cho.rule.engine.common.enums.ExpressOperationEnum;
import com.jd.cho.rule.engine.common.enums.FactorTypeEnum;
import com.jd.cho.rule.engine.common.enums.RelationTypeEnum;
import com.jd.cho.rule.engine.common.enums.VarTypeEnum;
import com.jd.cho.rule.engine.common.exceptions.BizErrorEnum;
import com.jd.cho.rule.engine.common.exceptions.BusinessException;
import com.jd.cho.rule.engine.common.util.AssertUtil;
import com.jd.cho.rule.engine.common.util.QlExpressUtil;
import com.jd.cho.rule.engine.domain.model.*;
import com.jd.cho.rule.engine.service.dto.RulePackDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 规则协议解析器-规则集合
 *
 * @author chenhonghao12
 * @version 1.0
 */
@Slf4j
public class RuleDefExpressionParser extends CommonExpressionParser {


    @Override
    void checkRuleConditionInner(RulePackDTO rulePackDTO, Map<String, RuleFactor> ruleFactorMap) {
        List<RuleDef> ruleDefs = JSON.parseArray(rulePackDTO.getRuleContent(), RuleDef.class);
        ruleDefs.stream().map(RuleDef::getRuleCondition).forEach(each -> checkRuleCondition(each, ruleFactorMap));
    }

    @Override
    Set<String> getFactorScriptParamInner(RulePackDTO rulePackDTO) {
        List<RuleDef> ruleDefs = JSON.parseArray(rulePackDTO.getRuleContent(), RuleDef.class);
        List<RuleCondition> conditions = ruleDefs.stream().map(RuleDef::getRuleCondition).collect(Collectors.toList());

        Set<String> factorCodes = Sets.newHashSet();
        conditions.forEach(each -> findFactorCodes(each, factorCodes));
        return factorCodes;
    }


    /**
     * 递归获取表达式中的字段
     *
     * @param ruleCondition 规则条件
     * @param resultCodes   结果
     */
    private static void findFactorCodes(RuleCondition ruleCondition, Set<String> resultCodes) {
        Optional.ofNullable(ruleCondition.getLeftVar()).ifPresent(leftVar -> {
            if (VarTypeEnum.FACTOR.getCode().equals(leftVar.getRuleType())) {
                resultCodes.add(leftVar.getCode());
            }
        });
        Optional.ofNullable(ruleCondition.getRightVar()).ifPresent(rightVar -> {
            if (VarTypeEnum.FACTOR.getCode().equals(rightVar.getRuleType())) {
                resultCodes.add(rightVar.getCode());
            }
        });
        if (CollectionUtils.isNotEmpty(ruleCondition.getChildren())) {
            ruleCondition.getChildren().forEach(each -> findFactorCodes(each, resultCodes));
        }
    }

    /**
     * 校验规则是否合法
     *
     * @param ruleCondition 规则协议
     * @param ruleFactorMap 规则因子信息
     */
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
                // 判断规则code是否合法存在
                RuleFactor leftFactor = factorExist(ruleCondition.getLeftVar(), ruleFactorMap);
                if (Objects.nonNull(ruleCondition.getRightVar())) {
                    factorExist(ruleCondition.getRightVar(), ruleFactorMap);
                }

                // 如果是规则因子，判断因子类型和操作符是否相匹配
                if (Objects.nonNull(leftFactor)) {
                    FactorTypeEnum factorType = leftFactor.getFactorType();
                    AssertUtil.isNotNull(factorType, BizErrorEnum.FACTOR_TYPE_IS_NOT_EXIST);

                    ExpressOperationEnum byCode = ExpressOperationEnum.getByCode(ruleCondition.getCompareOperation());
                    AssertUtil.isTrue(byCode.getGroup().equals(factorType.getCode()), BizErrorEnum.FACTOR_TYPE_AND_OPERATE_NOT_MATCH);
                }
            }
        }
    }

    public static RuleFactor factorExist(BasicVar leftVar, Map<String, RuleFactor> ruleFactorMap) {
        AssertUtil.isNotNull(leftVar, BizErrorEnum.LEFT_VAR_IS_NOT_EXIST);
        if (VarTypeEnum.FACTOR.getCode().equals(leftVar.getRuleType())) {
            AssertUtil.isNotBlank(leftVar.getCode(), BizErrorEnum.FACTOR_CODE_IS_NOT_EXIST);
            AssertUtil.isNotBlank(leftVar.getOriginalFactorCode(), BizErrorEnum.ORIGINAL_FACTOR_CODE_IS_NOT_EXIST);
            RuleFactor ruleFactor = ruleFactorMap.get(leftVar.getOriginalFactorCode());
            AssertUtil.isNotNull(ruleFactor, BizErrorEnum.RULE_FACTOR_DOES_NOT_EXIST);
            return ruleFactor;
        }
        return null;
    }


    /**
     * 构建当表达式
     * 该方法用于根据逻辑表达式对象构建相应的当表达式（用于规则引擎等场景）
     * 当表达式可以是简单的比较表达式，也可以是复合的逻辑表达式
     *
     * @param ruleCondition 逻辑表达式对象，包含构建当表达式所需的信息
     * @return 返回构建好的当表达式字符串
     */
    public static String buildWhenExpression(RuleCondition ruleCondition, Map<String, String> fieldMapping) {
        if (Objects.isNull(ruleCondition)) {
            return Dict.SYMBOL_EMPTY;
        }

        StringBuilder mvelExpression = new StringBuilder();
        String type = StringUtils.isNotBlank(ruleCondition.getLogicOperation()) ? Dict.RELATION_TYPE : Dict.EXPRESSION_TYPE;

        switch (type) {
            case Dict.EXPRESSION_TYPE:
                BasicVar leftVar = ruleCondition.getLeftVar();
                AssertUtil.isNotNull(leftVar);
                String compareOperation = ruleCondition.getCompareOperation();
                mvelExpression.append(buildOperatorExpress(compareOperation, ruleCondition.getLeftVar(), ruleCondition.getRightVar(), fieldMapping));
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
                    String childExpression = buildWhenExpression(child, fieldMapping);
                    if (!childExpression.isEmpty()) {
                        if (StringUtils.isNotBlank(currentExpression)) {
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
     * @param operator 操作符
     * @return 构建好的表达式
     */
    public static String buildOperatorExpress(String operator, BasicVar leftVar, BasicVar rightVar, Map<String, String> fieldMapping) {
        ExpressOperationEnum operation = ExpressOperationEnum.getByCode(operator);
        AssertUtil.isNotNull(operation);


        String expression = operation.getExpression();
        return String.format(expression, resolveBasicVar(leftVar, fieldMapping), resolveBasicVar(rightVar, fieldMapping));
    }

    private static Object resolveBasicVar(BasicVar basicVar, Map<String, String> fieldMapping) {
        String key = null;
        if (Objects.isNull(basicVar)) {
            return null;
        }
        if (VarTypeEnum.FACTOR.getCode().equals(basicVar.getRuleType())) {
            key = basicVar.getCode();
            String originalFactorCode = basicVar.getOriginalFactorCode();
            if (!Objects.equals(originalFactorCode, key)) {
                fieldMapping.put(key, originalFactorCode);
            }
            return key;
        } else if (VarTypeEnum.CONSTANT.getCode().equals(basicVar.getRuleType())) {
            return basicVar.getValues();
        } else if (VarTypeEnum.METHOD.getCode().equals(basicVar.getRuleType())) {
            CustomMethod customMethod = QlExpressUtil.CUSTOM_METHODS.stream().filter(each -> each.getMethodCode().equals(basicVar.getCode())).findFirst().orElse(null);
            AssertUtil.isNotNull(customMethod);
            Object[] array = basicVar.getParams().stream().map(each -> resolveBasicVar(each, fieldMapping)).toArray();
            return String.format(customMethod.getMethodExpression(), array);
        }
        return key;
    }

}
