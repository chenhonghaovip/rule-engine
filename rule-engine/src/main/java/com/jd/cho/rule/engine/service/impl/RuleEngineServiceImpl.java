package com.jd.cho.rule.engine.service.impl;

import com.jd.cho.rule.engine.domain.gateway.RuleEngineGateway;
import com.jd.cho.rule.engine.service.RuleEngineService;
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
//
//    @Override
//    public boolean execute(AbstractRuleGroup abstractRuleGroup, Map<String, Object> context) {
//        return abstractRuleGroup.execute(context);
//    }
}
