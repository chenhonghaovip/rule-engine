package com.jd.cho.rule.engine.spi;


import com.jd.cho.rule.engine.common.util.ApplicationUtils;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.model.RuleDef;

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

    /**
     * 获取规则定义信息
     *
     * @return 规则定义信息
     */
    protected List<RuleDef> getRuleDefEntities() {
        RuleConfigGateway ruleDomainService = ApplicationUtils.getBeans(RuleConfigGateway.class);
        return ruleDomainService.ruleDefQuery(ruleCodes);
    }
}
