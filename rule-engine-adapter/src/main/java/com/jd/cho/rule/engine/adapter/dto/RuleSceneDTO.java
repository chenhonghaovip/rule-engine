package com.jd.cho.rule.engine.adapter.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 规则场景表
 *
 * @author chenhonghao12
 */
@Data
public class RuleSceneDTO implements Serializable {

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
     * 规则分组信息
     */
    private List<RuleFactorGroupDTO> ruleFactorGroups;

    /**
     * 规则动作信息
     */
    private List<RuleActionDTO> ruleSceneActions;


}