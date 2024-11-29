package com.jd.cho.rule.engine.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author chenhonghao12
 */
@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class RuleFactorQueryDTO {
    /**
     * 分组编码
     */
    private String groupCode;

    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 分组下规则因子集合
     */
    private List<RuleFactorDTO> ruleFactorBeans;


}