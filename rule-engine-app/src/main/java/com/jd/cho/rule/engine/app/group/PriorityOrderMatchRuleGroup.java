package com.jd.cho.rule.engine.app.group;


import com.jd.cho.rule.engine.infr.common.ApplicationUtils;

import java.util.List;
import java.util.Map;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public class PriorityOrderMatchRuleGroup extends AbstractRuleGroup {
    public PriorityOrderMatchRuleGroup(List<String> ruleCodes) {
        super(ruleCodes);
    }

    @Override
    public boolean execute(Map<String, Object> context) {
        AbstractEngineProtocolService beans = ApplicationUtils.getBeans(AbstractEngineProtocolService.class);
        for (RuleDefEntity ruleFactorEntity : getRuleDefEntities()) {
            if (beans.execute(ruleFactorEntity, context)) {
                return true;
            }
        }
        return false;
    }
}
