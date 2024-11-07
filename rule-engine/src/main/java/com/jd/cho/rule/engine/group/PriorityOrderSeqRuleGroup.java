package com.jd.cho.rule.engine.group;

import com.jd.cho.rule.engine.common.util.ApplicationUtils;
import com.jd.cho.rule.engine.domain.gateway.RuleEngineGateway;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import com.jd.cho.rule.engine.spi.AbstractRuleGroup;

import java.util.List;
import java.util.Map;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public class PriorityOrderSeqRuleGroup extends AbstractRuleGroup {
    public PriorityOrderSeqRuleGroup(List<String> ruleCodes) {
        super(ruleCodes);
    }

    @Override
    public boolean execute(Map<String, Object> context) {
        RuleEngineGateway ruleEngineGateway = ApplicationUtils.getBeans(RuleEngineGateway.class);
        List<RuleDef> ruleDefEntities = getRuleDefEntities();
        for (RuleDef ruleDef : ruleDefEntities) {
            ruleEngineGateway.execute(ruleDef, context);
        }
        return true;
    }

}
