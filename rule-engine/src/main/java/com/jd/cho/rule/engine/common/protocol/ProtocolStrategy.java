package com.jd.cho.rule.engine.common.protocol;

import com.jd.cho.rule.engine.common.enums.RulePackTypeEnum;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import com.jd.cho.rule.engine.domain.model.RulePack;
import com.jd.cho.rule.engine.service.dto.RulePackDTO;

import java.util.Map;
import java.util.Set;

import static com.jd.cho.rule.engine.common.protocol.CommonExpressionParser.MAP;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public class ProtocolStrategy {

    static {
        MAP.put(RulePackTypeEnum.NORMAL.getCode(), new RuleDefExpressionParser());
        MAP.put(RulePackTypeEnum.DECISION_SET.getCode(), new RuleDefExpressionParser());
    }


    public static void checkRuleCondition(RulePackDTO rulePackDTO, Map<String, RuleFactor> ruleFactorMap) {
        MAP.get(rulePackDTO.getRulePackType().getCode()).checkRuleConditionInner(rulePackDTO, ruleFactorMap);
    }


    /**
     * 获取当前包中所需变量信息
     *
     * @param rulePackDTO 变量信息
     * @return key:因子-value:因子入参
     */
    public static Set<String> getFactorScriptParam(RulePackDTO rulePackDTO) {
        return MAP.get(rulePackDTO.getRulePackType().getCode()).getFactorScriptParamInner(rulePackDTO);
    }

    /**
     * 获取表达式
     *
     * @param rulePack rulePackDTO
     * @return string
     */
    public static String getQlExpress(RulePack rulePack) {
        // TODO 待实现
//        return MAP.get(rulePackDTO.getRulePackType().getCode()).getFactorScriptParamInner(rulePackDTO);
        return null;
    }


}
