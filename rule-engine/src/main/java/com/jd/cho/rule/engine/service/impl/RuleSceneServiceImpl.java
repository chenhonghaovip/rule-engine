package com.jd.cho.rule.engine.service.impl;

import com.jd.cho.rule.engine.common.convert.RuleSceneConvert;
import com.jd.cho.rule.engine.common.exceptions.BizErrorEnum;
import com.jd.cho.rule.engine.common.util.AssertUtil;
import com.jd.cho.rule.engine.controller.VO.resp.RuleSceneResp;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.model.RuleScene;
import com.jd.cho.rule.engine.service.RuleSceneService;
import com.jd.cho.rule.engine.service.dto.RuleSceneDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class RuleSceneServiceImpl implements RuleSceneService {

    @Resource
    private RuleConfigGateway ruleConfigGateway;


    @Override
    public List<RuleSceneResp> queryRuleScene() {
        List<RuleScene> ruleScenes = ruleConfigGateway.queryRuleScene();
        return ruleScenes.stream().map(RuleSceneConvert.INSTANCE::doToResp).collect(Collectors.toList());
    }

    @Override
    public String createRuleScene(RuleSceneDTO ruleSceneDTO) {
        AssertUtil.isNotNull(ruleSceneDTO, BizErrorEnum.DOES_NOT_EXIST);
        ruleSceneDTO.setId(null);
        RuleScene ruleScene = RuleSceneConvert.INSTANCE.doToEntity(ruleSceneDTO);
        if (StringUtils.isBlank(ruleScene.getSceneCode())) {
            String sceneCode = UUID.randomUUID().toString();
            ruleScene.setSceneCode(sceneCode);
        }
        return ruleConfigGateway.createRuleScene(ruleScene);
    }

    @Override
    public void updateRuleScene(RuleSceneDTO ruleSceneDTO) {
        AssertUtil.isNotNull(ruleSceneDTO, BizErrorEnum.DOES_NOT_EXIST);
        AssertUtil.isNotNull(ruleSceneDTO.getId(), BizErrorEnum.DOES_NOT_EXIST);

        RuleScene ruleScene = RuleSceneConvert.INSTANCE.doToEntity(ruleSceneDTO);
        ruleConfigGateway.updateRuleScene(ruleScene);
    }
}
