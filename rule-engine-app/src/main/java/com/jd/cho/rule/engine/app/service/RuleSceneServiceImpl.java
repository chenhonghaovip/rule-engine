package com.jd.cho.rule.engine.app.service;

import com.jd.cho.rule.engine.client.api.RuleSceneService;
import com.jd.cho.rule.engine.client.dto.RuleSceneDTO;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class RuleSceneServiceImpl implements RuleSceneService {

    @Resource
    private RuleConfigGateway ruleConfigGateway;

    @Override
    public String createScene(RuleSceneDTO ruleSceneDTO) {
        return null;
    }

}
