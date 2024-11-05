package com.jd.cho.rule.engine.client.api;

import com.jd.cho.rule.engine.client.dto.RuleSceneDTO;

import java.util.List;

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


    /**
     * 通过查询场景code获取对应的因子信息
     *
     * @param sceneCode 场景code
     * @return 规则因子集合
     */
    List<RuleSceneDTO> queryBySceneCode(String sceneCode);
}
