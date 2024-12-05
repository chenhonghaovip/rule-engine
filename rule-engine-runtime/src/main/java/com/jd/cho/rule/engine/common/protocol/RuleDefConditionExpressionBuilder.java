package com.jd.cho.rule.engine.common.protocol;

import com.google.common.collect.Lists;
import com.jd.cho.rule.engine.common.dict.Dict;
import com.jd.cho.rule.engine.common.enums.RelationTypeEnum;
import com.jd.cho.rule.engine.common.enums.VarTypeEnum;
import com.jd.cho.rule.engine.common.exceptions.BizErrorEnum;
import com.jd.cho.rule.engine.common.exceptions.BusinessException;
import com.jd.cho.rule.engine.common.util.AssertUtil;
import com.jd.cho.rule.engine.core.factor.RuleFactorTypeLoader;
import com.jd.cho.rule.engine.core.factor.model.ComparativeOperator;
import com.jd.cho.rule.engine.core.runner.CoreExpressionRunner;
import com.jd.cho.rule.engine.domain.model.BasicVar;
import com.jd.cho.rule.engine.domain.model.CustomMethod;
import com.jd.cho.rule.engine.domain.model.RuleCondition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class RuleDefConditionExpressionBuilder {
    private RuleFactorTypeLoader ruleFactorTypeLoader;
    private CoreExpressionRunner coreExpressionRunner;

    /**
     * 构建当表达式
     * 该方法用于根据逻辑表达式对象构建相应的当表达式（用于规则引擎等场景）
     * 当表达式可以是简单的比较表达式，也可以是复合的逻辑表达式
     *
     * @param ruleCondition 逻辑表达式对象，包含构建当表达式所需的信息
     * @return 返回构建好的当表达式字符串
     */
    public String buildWhenExpression(RuleCondition ruleCondition, Map<String, String> fieldMapping) {
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
    public String buildOperatorExpress(String operator, BasicVar leftVar, BasicVar rightVar, Map<String, String> fieldMapping) {
        ComparativeOperator operation = ruleFactorTypeLoader.getComparativeOperator(operator);
        AssertUtil.isNotNull(operation);
        String expression = operation.getExpression();
        return String.format(expression, resolveBasicVar(leftVar, fieldMapping), resolveBasicVar(rightVar, fieldMapping));
    }

    private Object resolveBasicVar(BasicVar basicVar, Map<String, String> fieldMapping) {
        String key;
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
            CustomMethod customMethod = coreExpressionRunner.getCustomMethod(basicVar.getCode());
            AssertUtil.isNotNull(customMethod);
            CustomMethod.CustomMethodParam customMethodParam;
            if (customMethod.getParamCount() != basicVar.getParams().size()) {
                throw new BusinessException(BizErrorEnum.PARAM_COUNT_NOT_MATCH);
            }

            List<Object> params = Lists.newArrayList();
            for (int i = 0; i < customMethod.getCustomMethodParams().size(); i++) {
                customMethodParam = customMethod.getCustomMethodParams().get(i);
                BasicVar methodParam = basicVar.getParams().get(i);

                // 如果方法参数类型为非内置，则方法入参无法接收前端常量，只能是变量或者方法
                if (!customMethodParam.getIsSysClassType() && VarTypeEnum.CONSTANT.getCode().equals(methodParam.getRuleType())) {
                    throw new BusinessException(BizErrorEnum.PARAM_TYPE_NOT_MATCH);
                }

                Object o = resolveBasicVar(methodParam, fieldMapping);
                if (VarTypeEnum.CONSTANT.getCode().equals(methodParam.getRuleType()) && customMethodParam.getParamType().isAssignableFrom(String.class)) {
                    String s = "\"" + o + "\"";
                    params.add(s);
                } else {
                    params.add(o);
                }
            }
            return String.format(customMethod.getMethodExpression(), params.toArray());
        }
        return null;
    }

}
