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
public class PriorityOrderMatchRuleGroup extends AbstractRuleGroup {

    @Override
    public boolean execute(List<RuleDef> list, Map<String, Object> context) {
        RuleEngineGateway ruleEngineGateway = ApplicationUtils.getBeans(RuleEngineGateway.class);

        for (RuleDef ruleDef : list) {
            if (ruleEngineGateway.execute(ruleDef, context)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String getCode() {
        return "1";
    }


}
