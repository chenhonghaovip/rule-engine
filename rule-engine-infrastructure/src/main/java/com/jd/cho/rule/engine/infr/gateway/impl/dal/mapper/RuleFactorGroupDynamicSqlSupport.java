package com.jd.cho.rule.engine.infr.gateway.impl.dal.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;

public final class RuleFactorGroupDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    public static final RuleFactorGroup ruleFactorGroup = new RuleFactorGroup();

    /**
     * Database Column Remarks:
     * 主键
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor_group.id")
    public static final SqlColumn<Long> id = ruleFactorGroup.id;

    /**
     * Database Column Remarks:
     * 分组编码
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor_group.group_code")
    public static final SqlColumn<String> groupCode = ruleFactorGroup.groupCode;

    /**
     * Database Column Remarks:
     * 分组名称
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor_group.group_name")
    public static final SqlColumn<String> groupName = ruleFactorGroup.groupName;

    /**
     * Database Column Remarks:
     * 是否删除 1-否，0-是
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor_group.yn")
    public static final SqlColumn<Boolean> yn = ruleFactorGroup.yn;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor_group.create_time")
    public static final SqlColumn<Date> createTime = ruleFactorGroup.createTime;

    /**
     * Database Column Remarks:
     * 更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor_group.modify_time")
    public static final SqlColumn<Date> modifyTime = ruleFactorGroup.modifyTime;

    /**
     * Database Column Remarks:
     * 创建人
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor_group.creator")
    public static final SqlColumn<String> creator = ruleFactorGroup.creator;

    /**
     * Database Column Remarks:
     * 修改人
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor_group.modifier")
    public static final SqlColumn<String> modifier = ruleFactorGroup.modifier;

    /**
     * Database Column Remarks:
     * 租户
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_factor_group.tenant")
    public static final SqlColumn<String> tenant = ruleFactorGroup.tenant;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_factor_group")
    public static final class RuleFactorGroup extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> groupCode = column("group_code", JDBCType.VARCHAR);

        public final SqlColumn<String> groupName = column("group_name", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> yn = column("yn", JDBCType.BIT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> modifyTime = column("modify_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public final SqlColumn<String> tenant = column("tenant", JDBCType.VARCHAR);

        public RuleFactorGroup() {
            super("rule_factor_group");
        }
    }
}