package com.jd.cho.rule.engine.client.extend;


import java.util.List;
import java.util.Map;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public abstract class AbstractRuleGroup {
    protected List<String> ruleCodes;

    public AbstractRuleGroup(List<String> ruleCodes) {
        this.ruleCodes = ruleCodes;
    }

    public abstract boolean execute(Map<String, Object> context);
}
