package com.jd.cho.rule.engine.common.protocol.checker;

import com.alibaba.fastjson.JSON;
import com.jd.cho.rule.engine.common.enums.RelationTypeEnum;
import com.jd.cho.rule.engine.common.enums.VarTypeEnum;
import com.jd.cho.rule.engine.common.exceptions.BizErrorEnum;
import com.jd.cho.rule.engine.common.exceptions.BusinessException;
import com.jd.cho.rule.engine.common.util.AssertUtil;
import com.jd.cho.rule.engine.domain.model.BasicVar;
import com.jd.cho.rule.engine.domain.model.RuleCondition;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import com.jd.cho.rule.engine.factor.RuleFactorTypeLoader;
import com.jd.cho.rule.engine.factor.model.ComparativeOperator;
import com.jd.cho.rule.engine.factor.model.RuleFactorType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * @author chenhonghao12
 */
@Service
@Slf4j
@AllArgsConstructor
public class RuleConditionChecker {
    private RuleFactorTypeLoader ruleFactorTypeLoader;

    public void check(RuleCondition ruleCondition, Map<String, RuleFactor> ruleFactorMap) {
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
    private void checkRuleConditionInner(RuleCondition ruleCondition, Map<String, RuleFactor> ruleFactorMap) {
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
                    RuleFactorType factorType = leftFactor.getFactorType();
                    AssertUtil.isNotNull(factorType, BizErrorEnum.FACTOR_TYPE_IS_NOT_EXIST);

                    ComparativeOperator comparativeOperator = ruleFactorTypeLoader.getComparativeOperator(ruleCondition.getCompareOperation());
                    AssertUtil.isNotNull(comparativeOperator, BizErrorEnum.EXPRESS_OPERATION_DOES_NOT_EXIST);

                    AssertUtil.isTrue(Objects.equals(comparativeOperator.getCode(), factorType.getCode()), BizErrorEnum.FACTOR_TYPE_AND_OPERATE_NOT_MATCH);
                }
            }
        }
    }

    private static RuleFactor factorExist(BasicVar basicVar, Map<String, RuleFactor> ruleFactorMap) {
        AssertUtil.isNotNull(basicVar, BizErrorEnum.LEFT_VAR_IS_NOT_EXIST);
        if (VarTypeEnum.FACTOR.getCode().equals(basicVar.getRuleType())) {
            AssertUtil.isNotBlank(basicVar.getCode(), BizErrorEnum.FACTOR_CODE_IS_NOT_EXIST);
            AssertUtil.isNotBlank(basicVar.getOriginalFactorCode(), BizErrorEnum.ORIGINAL_FACTOR_CODE_IS_NOT_EXIST);
            RuleFactor ruleFactor = ruleFactorMap.get(basicVar.getOriginalFactorCode());
            AssertUtil.isNotNull(ruleFactor, BizErrorEnum.RULE_FACTOR_DOES_NOT_EXIST);
            return ruleFactor;
        }
        return null;
    }
}
