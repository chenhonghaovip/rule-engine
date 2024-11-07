package com.jd.cho.rule.engine.common.client.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 规则场景行为表
 *
 * @author chenhonghao12
 * @TableName rule_scene_action
 */
@Data
public class RuleSceneActionDTO implements Serializable {

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

    /**
     * 是否删除 1-否，0-是
     */
    private Integer yn;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date modifyTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 租户
     */
    private String tenant;

}