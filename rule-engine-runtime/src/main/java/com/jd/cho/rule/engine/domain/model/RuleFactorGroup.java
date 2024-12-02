package com.jd.cho.rule.engine.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
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

    /**
     * 父分组编码
     */
    private String parentGroupCode;

    /**
     * 子分组列表
     */
    private List<RuleFactorGroup> ruleFactorGroups;

}
