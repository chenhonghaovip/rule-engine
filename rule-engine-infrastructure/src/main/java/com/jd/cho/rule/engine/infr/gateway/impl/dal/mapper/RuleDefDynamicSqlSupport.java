package com.jd.cho.rule.engine.infr.gateway.impl.dal.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;

public final class RuleDefDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    public static final RuleDef ruleDef = new RuleDef();

    /**
     * Database Column Remarks:
     * 主键
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_def.id")
    public static final SqlColumn<Long> id = ruleDef.id;

    /**
     * Database Column Remarks:
     * 规则编码
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_def.rule_code")
    public static final SqlColumn<String> ruleCode = ruleDef.ruleCode;

    /**
     * Database Column Remarks:
     * 规则名称
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_def.rule_name")
    public static final SqlColumn<String> ruleName = ruleDef.ruleName;

    /**
     * Database Column Remarks:
     * 版本号
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_def.version")
    public static final SqlColumn<Integer> version = ruleDef.version;

    /**
     * Database Column Remarks:
     * 是否最新版本（0:否，1:是）
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_def.latest")
    public static final SqlColumn<Integer> latest = ruleDef.latest;

    /**
     * Database Column Remarks:
     * 优先级
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_def.priority")
    public static final SqlColumn<Integer> priority = ruleDef.priority;

    /**
     * Database Column Remarks:
     * 备注
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_def.remark")
    public static final SqlColumn<String> remark = ruleDef.remark;

    /**
     * Database Column Remarks:
     * 状态（0:否，1:是）
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_def.status")
    public static final SqlColumn<Boolean> status = ruleDef.status;

    /**
     * Database Column Remarks:
     * 是否删除 1-否，0-是
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_def.yn")
    public static final SqlColumn<Boolean> yn = ruleDef.yn;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_def.create_time")
    public static final SqlColumn<Date> createTime = ruleDef.createTime;

    /**
     * Database Column Remarks:
     * 更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_def.modify_time")
    public static final SqlColumn<Date> modifyTime = ruleDef.modifyTime;

    /**
     * Database Column Remarks:
     * 创建人
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_def.creator")
    public static final SqlColumn<String> creator = ruleDef.creator;

    /**
     * Database Column Remarks:
     * 修改人
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_def.modifier")
    public static final SqlColumn<String> modifier = ruleDef.modifier;

    /**
     * Database Column Remarks:
     * 租户
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_def.tenant")
    public static final SqlColumn<String> tenant = ruleDef.tenant;

    /**
     * Database Column Remarks:
     * 规则条件
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_def.rule_condition")
    public static final SqlColumn<String> ruleCondition = ruleDef.ruleCondition;

    /**
     * Database Column Remarks:
     * 规则行为
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source field: rule_def.rule_action")
    public static final SqlColumn<String> ruleAction = ruleDef.ruleAction;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: rule_def")
    public static final class RuleDef extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> ruleCode = column("rule_code", JDBCType.VARCHAR);

        public final SqlColumn<String> ruleName = column("rule_name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> version = column("version", JDBCType.INTEGER);

        public final SqlColumn<Integer> latest = column("latest", JDBCType.INTEGER);

        public final SqlColumn<Integer> priority = column("priority", JDBCType.INTEGER);

        public final SqlColumn<String> remark = column("remark", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> status = column("`status`", JDBCType.BIT);

        public final SqlColumn<Boolean> yn = column("yn", JDBCType.BIT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> modifyTime = column("modify_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public final SqlColumn<String> tenant = column("tenant", JDBCType.VARCHAR);

        public final SqlColumn<String> ruleCondition = column("rule_condition", JDBCType.LONGVARCHAR);

        public final SqlColumn<String> ruleAction = column("rule_action", JDBCType.LONGVARCHAR);

        public RuleDef() {
            super("rule_def");
        }
    }
}