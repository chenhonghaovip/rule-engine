package com.jd.cho.rule.engine.common.protocol.parser;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import com.jd.cho.rule.engine.common.enums.VarTypeEnum;
import com.jd.cho.rule.engine.common.exceptions.BizErrorEnum;
import com.jd.cho.rule.engine.common.protocol.RulePackDTO;
import com.jd.cho.rule.engine.common.util.AssertUtil;
import com.jd.cho.rule.engine.domain.model.RuleCondition;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RulePackDTOFactorParser {
    public Set<String> getFactorScriptParam(RulePackDTO rulePackDTO) {
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
}
