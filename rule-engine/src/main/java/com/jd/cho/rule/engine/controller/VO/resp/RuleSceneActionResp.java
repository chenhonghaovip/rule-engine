package com.jd.cho.rule.engine.controller.VO.resp;

import lombok.Data;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class RuleSceneActionResp {
    /**
     * 主键
     */
    private Long id;

    /**
     * 场景编码
     */
    private String sceneCode;

    /**
     * 行为编码
     */
    private String actionCode;

    /**
     * 行为类型
     */
    private String actionType;

    /**
     * 行为内容
     */
    private String action;
}
