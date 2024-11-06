package com.jd.cho.rule.engine.client.api;

import com.jd.cho.rule.engine.client.dto.RuleSceneDTO;

/**
 * 规则场景服务接口
 *
 * @author chenhonghao12
 * @version 1.0
 */
public interface RuleSceneService {

    /**
     * 创建规则场景
     *
     * @return 规则场景code
     */
    String createScene(RuleSceneDTO ruleSceneDTO);


}
