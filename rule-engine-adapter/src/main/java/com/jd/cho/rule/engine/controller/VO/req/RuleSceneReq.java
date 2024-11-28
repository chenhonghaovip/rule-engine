package com.jd.cho.rule.engine.controller.VO.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class RuleSceneReq implements Serializable {

    private Long id;
    /**
     * 场景code
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
     * 场景下规则因子分组
     */
    private List<RuleFactorGroupReq> ruleFactorGroups;

    /**
     * 场景下动作列表
     */
    private List<RuleActionReq> ruleSceneActions;

}
