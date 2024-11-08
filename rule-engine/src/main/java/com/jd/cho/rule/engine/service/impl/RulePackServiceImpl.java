package com.jd.cho.rule.engine.service.impl;

import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.service.RulePackService;
import com.jd.cho.rule.engine.service.dto.RulePackDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class RulePackServiceImpl implements RulePackService {

    @Resource
    private RuleConfigGateway ruleConfigGateway;

    @Override
    public String createRule(RulePackDTO rulePackDTO) {

        return null;
    }

    @Override
    public void updateRule(RulePackDTO rulePackDTO) {

    }

    @Override
    public List<RulePackDTO> queryByRuleCodes(List<String> rulePackCodes) {
        return null;
    }

    @Override
    public List<RulePackDTO> queryByRuleCodes(String rulePackCode) {
        return null;
    }

    @Override
    public List<String> queryParams(String rulePackCode) {
        return null;
    }
}
