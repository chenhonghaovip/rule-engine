package com.jd.cho.rule.engine.common.protocol;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import com.jd.cho.rule.engine.common.enums.ExpressOperationEnum;
import com.jd.cho.rule.engine.common.enums.FactorTypeEnum;
import com.jd.cho.rule.engine.common.enums.RelationTypeEnum;
import com.jd.cho.rule.engine.common.enums.VarTypeEnum;
import com.jd.cho.rule.engine.common.exceptions.BizErrorEnum;
import com.jd.cho.rule.engine.common.exceptions.BusinessException;
import com.jd.cho.rule.engine.common.util.AssertUtil;
import com.jd.cho.rule.engine.domain.model.BasicVar;
import com.jd.cho.rule.engine.domain.model.RuleCondition;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
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
                AssertUtil.isNotBlank(leftVar.getOriginalFactorCode(), BizErrorEnum.FACTOR_CODE_IS_NOT_EXIST);
                resultCodes.add(leftVar.getOriginalFactorCode());
            }
        });
        Optional.ofNullable(ruleCondition.getRightVar()).ifPresent(rightVar -> {
            if (VarTypeEnum.FACTOR.getCode().equals(rightVar.getRuleType())) {
                AssertUtil.isNotBlank(rightVar.getOriginalFactorCode(), BizErrorEnum.FACTOR_CODE_IS_NOT_EXIST);
                resultCodes.add(rightVar.getOriginalFactorCode());
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

}
