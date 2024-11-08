package com.jd.cho.rule.engine.service.impl;

import com.jd.cho.rule.engine.service.RulePackService;
import com.jd.cho.rule.engine.service.dto.RuleDefQueryDTO;
import com.jd.cho.rule.engine.service.dto.RulePackDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class RulePackServiceImpl implements RulePackService {
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
    public List<RuleDefQueryDTO> queryByRuleCodes(String rulePackCode) {
        return null;
    }

    @Override
    public List<String> queryParams(String rulePackCode) {
        return null;
    }
}
