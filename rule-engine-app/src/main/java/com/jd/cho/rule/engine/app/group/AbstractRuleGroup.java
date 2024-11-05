package com.jd.cho.rule.engine.app.group;

import com.chh.experiment.infrastructure.engine.RuleDefEntity;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    protected List<RuleDefEntity> getRuleDefEntities() {
        List<RuleDefEntity> factorEntities = ruleCodes.stream().map(each -> RuleDefEntity.builder().ruleCode(each).build()).collect(Collectors.toList());
        return factorEntities.stream().sorted(Comparator.comparingInt(RuleDefEntity::getPriority)).collect(Collectors.toList());
    }
}
