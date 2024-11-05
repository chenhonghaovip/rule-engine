package com.jd.cho.rule.engine.infr.gateway.impl;

import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class RuleConfigGatewayImpl implements RuleConfigGateway {


    @Override
    public List<RuleDef> ruleDefQuery(List<String> ruleCodes) {
        return null;
    }
}
