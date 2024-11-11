package com.jd.cho.rule.engine.domain.model;

import lombok.Data;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
public class RuleFactorGroup {
    private Long id;
    /**
     * 分组编码
     */
    private String groupCode;

    /**
     * 分组名称
     */
    private String groupName;

}
