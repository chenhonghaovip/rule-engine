package com.jd.cho.rule.engine.domain.atomic;

import com.jd.cho.rule.engine.domain.model.RuleCondition;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

/**
 * 规则协议解析器
 *
 * @author chenhonghao12
 * @version 1.0
 */

public class RuleDefExpressionParser {


    public static void checkRuleCondition(RuleCondition ruleCondition, Map<String, RuleFactor> ruleFactors) {
        if (Objects.nonNull(ruleCondition)) {
            if (StringUtils.isNotBlank(ruleCondition.getLogicOperation())) {

            }
        }

    }
}
