package com.jd.cho.rule.engine.service.impl;

import com.jd.cho.rule.engine.adapter.convert.RuleSceneConvert;
import com.jd.cho.rule.engine.adapter.dto.RuleActionDTO;
import com.jd.cho.rule.engine.adapter.dto.RuleSceneDTO;
import com.jd.cho.rule.engine.common.exceptions.BizErrorEnum;
import com.jd.cho.rule.engine.common.util.AssertUtil;
import com.jd.cho.rule.engine.controller.VO.resp.RuleSceneResp;
import com.jd.cho.rule.engine.domain.gateway.RuleConfigGateway;
import com.jd.cho.rule.engine.domain.model.RuleScene;
import com.jd.cho.rule.engine.service.RuleSceneService;
import org.apache.commons.collections.CollectionUtils;
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
        baseCheck(ruleSceneDTO);

        RuleScene ruleScene = RuleSceneConvert.INSTANCE.doToEntity(ruleSceneDTO);
        if (StringUtils.isBlank(ruleScene.getSceneCode())) {
            String sceneCode = UUID.randomUUID().toString();
            ruleScene.setSceneCode(sceneCode);
        }
        ruleScene.setId(null);

        return ruleConfigGateway.createRuleScene(ruleScene);
    }

    @Override
    public void updateRuleScene(RuleSceneDTO ruleSceneDTO) {
        baseCheck(ruleSceneDTO);
        AssertUtil.isNotNull(ruleSceneDTO.getId(), BizErrorEnum.DOES_NOT_EXIST);

        RuleScene ruleScene = RuleSceneConvert.INSTANCE.doToEntity(ruleSceneDTO);
        ruleConfigGateway.updateRuleScene(ruleScene);
    }


    private void baseCheck(RuleSceneDTO ruleSceneDTO) {
        AssertUtil.isNotNull(ruleSceneDTO, BizErrorEnum.DOES_NOT_EXIST);
        if (CollectionUtils.isNotEmpty(ruleSceneDTO.getRuleSceneActions())) {
            boolean repeat = ruleSceneDTO.getRuleSceneActions().size() != ruleSceneDTO.getRuleSceneActions().stream().map(RuleActionDTO::getActionCode).distinct().count();
            AssertUtil.isFalse(repeat, BizErrorEnum.ACTION_CODE_IS_EXIST);
        }
    }
}
