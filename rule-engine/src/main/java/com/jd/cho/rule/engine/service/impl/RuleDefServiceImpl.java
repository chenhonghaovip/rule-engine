package com.jd.cho.rule.engine.service.impl;

import com.jd.cho.rule.engine.common.dto.RuleDefDTO;
import com.jd.cho.rule.engine.common.dto.RuleDefQueryDTO;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.service.RuleDefService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class RuleDefServiceImpl implements RuleDefService {

    @Resource
    private RuleConfigGateway ruleConfigGateway;

    @Override
    public String createRule(RuleDefDTO ruleDefDTO) {
        return null;
    }

    @Override
    public List<RuleDefDTO> batchCreateRule(List<RuleDefDTO> list) {
        return null;
    }

    @Override
    public void updateRule(RuleDefDTO ruleDefDTO) {

    }

    @Override
    public void batchUpdateRule(List<RuleDefDTO> list) {

    }

    @Override
    public List<RuleDefQueryDTO> queryByRuleCodes(List<String> ruleCodes) {
        return null;
    }

    @Override
    public List<RuleDefQueryDTO> queryByRuleCodes(String ruleCode) {
        return null;
    }

    @Override
    public List<String> queryParams(List<String> ruleCodes) {
        return null;
    }
}
