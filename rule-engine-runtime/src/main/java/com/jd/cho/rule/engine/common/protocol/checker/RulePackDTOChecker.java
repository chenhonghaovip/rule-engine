package com.jd.cho.rule.engine.common.protocol.checker;

import com.alibaba.fastjson.JSON;
import com.jd.cho.rule.engine.common.protocol.RulePackDTO;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class RulePackDTOChecker {
    private RuleConditionChecker ruleConditionChecker;

    public void check(RulePackDTO rulePackDTO, Map<String, RuleFactor> ruleFactorMap) {
        List<RuleDef> ruleDefs = JSON.parseArray(rulePackDTO.getRuleContent(), RuleDef.class);
        ruleDefs.stream().map(RuleDef::getRuleCondition).forEach(each -> ruleConditionChecker.check(each, ruleFactorMap));
    }
}
