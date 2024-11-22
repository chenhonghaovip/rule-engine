package com.jd.cho.rule.engine.service.impl;

import com.jd.cho.rule.engine.common.cache.ContextHolder;
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
    public boolean execute(String rulePackCode, Map<String, Object> context) {
        try {
            ContextHolder.setContext(context);
            return ruleEngineGateway.execute(rulePackCode, context);
        } finally {
            ContextHolder.clear();
        }
    }
}
