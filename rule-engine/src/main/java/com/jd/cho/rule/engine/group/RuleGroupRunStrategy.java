package com.jd.cho.rule.engine.group;


import com.jd.cho.rule.engine.spi.RuleGroupExtendService;

import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public class RuleGroupRunStrategy {
    private static Map<String, RuleGroupExtendService> ruleGroupMap;

    static {
        ServiceLoader<RuleGroupExtendService> load = ServiceLoader.load(RuleGroupExtendService.class);
        for (RuleGroupExtendService abstractRuleGroup : load) {
            ruleGroupMap.put(abstractRuleGroup.getCode(), abstractRuleGroup);
        }
    }


    public static RuleGroupExtendService getRuleGroup(String code) {
        return ruleGroupMap.get(code);
    }
}
