package com.jd.cho.rule.engine.infra.convert;

import com.jd.cho.rule.engine.core.factor.RuleFactorTypeLoader;
import com.jd.cho.rule.engine.dal.DO.RuleFactorDO;
import com.jd.cho.rule.engine.domain.model.RuleFactor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class InfraConverters {
    private final RuleFactorDOConvert ruleFactorDOConvert;
    private final RuleFactorTypeLoader ruleFactorTypeLoader;

    public RuleFactor doToEntity(RuleFactorDO ruleFactorDO) {
        RuleFactor ruleFactor = ruleFactorDOConvert.doToEntity(ruleFactorDO);
        if (ruleFactor != null) {
            ruleFactor.setFactorType(ruleFactorTypeLoader.getFactorType(ruleFactorDO.getFactorType()));
        }
        return ruleFactor;
    }
}
