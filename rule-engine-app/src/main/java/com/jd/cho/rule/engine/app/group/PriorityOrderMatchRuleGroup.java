package com.jd.cho.rule.engine.app.group;


import com.jd.cho.rule.engine.client.api.RuleEngineService;
import com.jd.cho.rule.engine.client.dto.RuleDefDTO;
import com.jd.cho.rule.engine.infr.common.ApplicationUtils;

import java.util.List;
import java.util.Map;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public class PriorityOrderMatchRuleGroup extends AbstractStandardRuleGroup {
    public PriorityOrderMatchRuleGroup(List<String> ruleCodes) {
        super(ruleCodes);
    }

    @Override
    public boolean execute(Map<String, Object> context) {
        RuleEngineService beans = ApplicationUtils.getBeans(RuleEngineService.class);
        List<RuleDefDTO> ruleDefEntities = getRuleDefEntities();
        for (RuleDefDTO ruleDefDTO : ruleDefEntities) {
            if (beans.execute(ruleDefDTO, context)) {
                return true;
            }
        }
        return false;
    }



}
