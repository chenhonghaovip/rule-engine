package com.jd.cho.rule.engine.app.group;

import com.jd.cho.rule.engine.client.extend.AbstractRuleGroup;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import com.jd.cho.rule.engine.infr.common.ApplicationUtils;

import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public abstract class AbstractStandardRuleGroup extends AbstractRuleGroup {
    public AbstractStandardRuleGroup(List<String> ruleCodes) {
        super(ruleCodes);
    }

    /**
     * 获取规则定义信息
     *
     * @return 规则定义信息
     */
    protected List<RuleDef> getRuleDefEntities() {
        RuleConfigGateway ruleDomainService = ApplicationUtils.getBeans(RuleConfigGateway.class);
        return ruleDomainService.ruleDefQuery(ruleCodes);
//        return ruleDefs.stream().map(each -> {
//            RuleDefDTO ruleDefDTO = new RuleDefDTO();
//            BeanUtils.copyProperties(each, ruleDefDTO);
//            return ruleDefDTO;
//        }).sorted(Comparator.comparingInt(RuleDefDTO::getPriority)).collect(Collectors.toList());
    }
}
