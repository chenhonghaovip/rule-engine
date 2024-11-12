package com.jd.cho.rule.engine.service.dto;

import com.jd.cho.rule.engine.common.enums.ConstantEnum;
import lombok.Data;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class RuleActionDTO {
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
    private ConstantEnum actionType;

    /**
     * 行为内容
     */
    private String action;
}
