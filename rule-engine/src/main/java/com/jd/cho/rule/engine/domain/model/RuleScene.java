package com.jd.cho.rule.engine.domain.model;

import lombok.Data;

import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class RuleScene {

    private Long id;
    /**
     * 场景编码
     */
    private String sceneCode;

    /**
     * 场景名称
     */
    private String sceneName;

    /**
     * 场景描述
     */
    private String sceneDesc;

    /**
     * 分组信息
     */
    private List<RuleFactorGroup> ruleFactorGroups;

    /**
     * 动作信息
     */
    private List<RuleSceneAction> ruleSceneActions;

}
