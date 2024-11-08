package com.jd.cho.rule.engine.group;

import com.jd.cho.rule.engine.common.util.ApplicationUtils;
import com.jd.cho.rule.engine.domain.gateway.RuleEngineGateway;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import com.jd.cho.rule.engine.spi.RuleGroupExtendService;

import java.util.List;
import java.util.Map;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public class PriorityOrderSeqRuleGroup implements RuleGroupExtendService {

    @Override
    public String getCode() {
        return "2";
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean execute(List<RuleDef> list, Map<String, Object> context) {
        RuleEngineGateway ruleEngineGateway = ApplicationUtils.getBeans(RuleEngineGateway.class);
        for (RuleDef ruleDef : list) {
            ruleEngineGateway.execute(ruleDef, context);
        }
        return true;
    }


}
