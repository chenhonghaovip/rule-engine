package com.jd.cho.rule.engine.app.service;

import com.jd.cho.rule.engine.client.api.RuleFactorService;
import com.jd.cho.rule.engine.client.dto.RuleFactorEditDTO;
import com.jd.cho.rule.engine.client.dto.RuleFactorQueryDTO;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class RuleFactorServiceImpl implements RuleFactorService {

    @Resource
    private RuleConfigGateway ruleConfigGateway;

    @Override
    public String createRuleFactor(RuleFactorEditDTO ruleFactorDTO) {
        return null;
    }

    @Override
    public void updateRuleFactor(RuleFactorEditDTO ruleFactorDTO) {

    }

    @Override
    public List<RuleFactorQueryDTO> queryBySceneCode(String sceneCode) {
        return null;
    }
}
