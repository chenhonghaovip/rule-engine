package com.jd.cho.rule.engine.app.service;

import com.jd.cho.rule.engine.client.api.RuleEngineService;
import com.jd.cho.rule.engine.client.extend.AbstractRuleGroup;
import com.jd.cho.rule.engine.domain.gateway.RuleEngineGateway;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class RuleEngineServiceImpl implements RuleEngineService {

    @Resource
    private RuleEngineGateway ruleEngineGateway;

    @Override
    public boolean execute(String ruleCode, Map<String, Object> context) {
        return ruleEngineGateway.execute(ruleCode, context);
    }

    @Override
    public boolean execute(AbstractRuleGroup abstractRuleGroup, Map<String, Object> context) {
        return abstractRuleGroup.execute(context);
    }
}
