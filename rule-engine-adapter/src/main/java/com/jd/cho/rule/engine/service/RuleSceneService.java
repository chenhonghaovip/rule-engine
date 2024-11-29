package com.jd.cho.rule.engine.service;

import com.jd.cho.rule.engine.controller.VO.resp.RuleSceneResp;
import com.jd.cho.rule.engine.adapter.dto.RuleSceneDTO;

import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
public interface RuleSceneService {

    /**
     * 查询规则场景
     *
     * @return List<RuleScene>
     */
    List<RuleSceneResp> queryRuleScene();

    /**
     * 创建规则场景配置信息
     *
     * @param ruleSceneDTO 参数
     * @return List<RuleScene>
     */
    String createRuleScene(RuleSceneDTO ruleSceneDTO);

    /**
     * 更新规则场景配置信息
     *
     * @param ruleSceneDTO 参数
     */
    void updateRuleScene(RuleSceneDTO ruleSceneDTO);

}
