package com.jd.cho.rule.engine.service.dto;

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
     * 分组编码
     */
    private String groupCode;

    /**
     * 规则分组信息
     */
    private List<RuleFactorGroupDTO> ruleFactorGroups;


}