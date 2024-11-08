package com.jd.cho.rule.engine.group;


import com.jd.cho.rule.engine.spi.AbstractRuleGroup;

import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public class RuleGroupRunStrategy {
    private static Map<String, AbstractRuleGroup> ruleGroupMap;

    static {
        ServiceLoader<AbstractRuleGroup> load = ServiceLoader.load(AbstractRuleGroup.class);
        for (AbstractRuleGroup abstractRuleGroup : load) {
            ruleGroupMap.put(abstractRuleGroup.getCode(), abstractRuleGroup);
        }
    }


    public static AbstractRuleGroup getRuleGroup(String code) {
        return ruleGroupMap.get(code);
    }
}
