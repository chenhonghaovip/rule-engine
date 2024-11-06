package com.jd.cho.rule.engine.infr.gateway.impl.dal.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.annotation.Generated;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table rule_factor_group
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class RuleFactorGroupDO extends BaseEntity {
    /**
     * Database Column Remarks:
     *   主键
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor_group.id")
    private Long id;

    /**
     * Database Column Remarks:
     *   分组编码
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor_group.group_code")
    private String groupCode;

    /**
     * Database Column Remarks:
     *   分组名称
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor_group.group_name")
    private String groupName;

    /**
     * Database Column Remarks:
     * 是否删除 1-否，0-是
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor_group.yn")
    private Boolean yn;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    public RuleFactorGroupDO withId(Long id) {
        this.setId(id);
        return this;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    public RuleFactorGroupDO withGroupCode(String groupCode) {
        this.setGroupCode(groupCode);
        return this;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    public RuleFactorGroupDO withGroupName(String groupName) {
        this.setGroupName(groupName);
        return this;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    public RuleFactorGroupDO withYn(Boolean yn) {
        this.setYn(yn);
        return this;
    }
}