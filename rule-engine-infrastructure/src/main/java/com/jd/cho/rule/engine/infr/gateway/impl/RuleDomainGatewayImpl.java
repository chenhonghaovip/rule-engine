package com.jd.cho.rule.engine.infr.gateway.impl;

import com.jd.cho.rule.engine.domain.gateway.RuleDomainGateway;
import com.jd.cho.rule.engine.domain.model.RuleDef;
import com.jd.cho.rule.engine.infr.gateway.impl.dal.mapper.RuleDefMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class RuleDomainGatewayImpl implements RuleDomainGateway {

    @Resource
    private RuleDefMapper ruleDefMapper;

    @Override
    public List<RuleDef> ruleDefQuery(List<String> ruleCodes) {
        return null;
    }
}
